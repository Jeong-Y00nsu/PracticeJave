package user;

import util.Cypher;
import util.MakeRandomStr;
import util.Result;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    private UserRepository userRepository;

    private Cypher cypher;
    private List<User> users;

    public UserService() {
        this.userRepository = new UserFileRepository();
        this.cypher = new Cypher();
        this.users = new ArrayList<User>();
    }

    public void setUsers() throws Exception{
        this.users = this.userRepository.read();
    }

    public void save() throws Exception{
        this.userRepository.write(this.users);
    }

    private boolean isDuplicatId(String id){
        for(User user: users){
            if(user.getId().equals(id)) return true;
        }
        return false;
    }

    public User findById(String id){
        for(User user: users){
            if(user.getId().equals(id)) return user;
        }
        return null;
    }

    public Result signIn(String id, String pw) throws Exception{
        User user = findById(id);

        if(user==null) return Result.INCORRECT;
        else if(!user.getPw().equals(cypher.encrypt(pw+user.getSalt()))) return Result.ONLY_ID_CORRECT;
        else return Result.OK;
    }

    public Result signUp(User user) throws Exception{
        if(isDuplicatId(user.getId())) return Result.DUPLICATE_ID;
        if(user.getPw()==null || user.getPw().equals(""))  return Result.INVALID_PARAM;

        user.setSalt(MakeRandomStr.makeRandomPk(5));
        user.setPlanFileUrl(user.getId()+".dat");
        user.setPw(cypher.encrypt(user.getPw()+user.getSalt()));

        users.add(user);
        return Result.OK;
    }

}

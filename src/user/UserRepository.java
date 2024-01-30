package user;

import java.util.List;

public interface UserRepository {

    public List<User> read() throws Exception;

    public void write(List<User> users) throws Exception;

}

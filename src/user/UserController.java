package user;

import plan.PlanService;
import util.Result;

import java.util.Scanner;

public class UserController {
    private UserService userService;
    private PlanService planService;

    private Scanner scan;

    private User user;

    public UserController() {
        this.userService = new UserService();
        this.scan = new Scanner(System.in);
    }

    public Result signIn(int cnt){
        if(--cnt <0 ) {
            System.out.println("처음 화면으로 돌아갑니다.");
            return Result.FAIL;
        }
        try{
            System.out.println("아이디: ");
            String id = scan.next();
            System.out.println("비밀번호: ");
            String pw = scan.next();

            Result result = userService.signIn(id, pw);
            if(result.equals(Result.INCORRECT)){
                System.out.println("+------------------------------------------------------------------------------------------------------------------------+");
                System.out.println("| 아이디가 틀렸습니다. 다시 시도해주세요. (남은 횟수: "+cnt+")                                                                   |");
                System.out.println("+------------------------------------------------------------------------------------------------------------------------+");

                return signIn(cnt);
            } else if(result.equals(Result.ONLY_ID_CORRECT)){
                System.out.println("+------------------------------------------------------------------------------------------------------------------------+");
                System.out.println("| 비밀번호가 틀렸습니다. 다시 시도해주세요. (남은 횟수: "+cnt+")                                                                  |");
                System.out.println("+------------------------------------------------------------------------------------------------------------------------+");

                return signIn(cnt);
            }
            this.user = userService.findById(id);
        }catch(Exception e){
            e.printStackTrace();
            return Result.FAIL;
        }
        return Result.OK;
    }

    public Result signUp(){
        int cnt = 0;
        try{
            Result result = null;
            do {
                if(result!=null&& result.equals(Result.INVALID_PARAM)) {
                    System.out.println("+------------------------------------------------------------------------------------------------------------------------+");
                    System.out.println("| 비밀번호를 입력해주세요.                                                                                                   |");
                    System.out.println("+------------------------------------------------------------------------------------------------------------------------+");
                } else if(result!=null&&result.equals(Result.DUPLICATE_ID)){
                    System.out.println("+------------------------------------------------------------------------------------------------------------------------+");
                    System.out.println("| 중복된 아이디입니다.                                                                                                      |");
                    System.out.println("+------------------------------------------------------------------------------------------------------------------------+");
                }
                System.out.println("사용하실 아이디를 입력해주세요.: ");
                String id = scan.next();
                System.out.println("사용하실 비밀번호를 입력해주세요.: ");
                String pw = scan.next();
                System.out.println("사용하실 이름을 알려주세요.: ");
                String name = scan.next();
                User user = new User(id, pw, name, null, null);
                result = userService.signUp(user);
                cnt++;
            } while(!result.equals(Result.OK));
        }catch(Exception e){
            e.printStackTrace();
            return Result.FAIL;
        }
        return Result.OK;
    }

    public Result save(){
        try {
            userService.save();
            planService.save(this.user);
        } catch (Exception e){
            e.printStackTrace();
            return Result.FAIL;
        }
        return Result.OK;
    }
    public void setUsers() throws Exception{
        userService.setUsers();
    }
}

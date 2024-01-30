import plan.PlanController;
import user.UserController;
import util.Result;
import view.ConsolePrint;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean isRun = true;
        boolean check = true;
        boolean isSignIn = false;
        Result result;
        int monthMoveCnt = 0;
        int command;


        Scanner scan = new Scanner(System.in);
        UserController userController = new UserController();
        PlanController planController = new PlanController();
        ConsolePrint consolePrint = new ConsolePrint();

        try{
            userController.setUsers();
        } catch (Exception e){
            e.printStackTrace();
        }

        while(isRun && !isSignIn){
            consolePrint.printHeaderPage();
            consolePrint.printLoginPage();
            command = scan.nextInt();
            switch(command){
                case 1:
                    consolePrint.printSignInHeader();
                    result =userController.signIn(3);
                    if(result.equals(Result.FAIL)) System.out.println("실패했습니다.");
                    else {
                        System.out.println("성공했습니다.");
                        isSignIn = true;
                    }
                    break;
                case 2:
                    consolePrint.printSignUpHeader();
                    result = userController.signUp();
                    if(result.equals(Result.FAIL)) System.out.println("실패했습니다.");
                    else System.out.println("성공했습니다.");
                    break;
                case 3:
                    System.out.println("종료합니다.");
                    userController.save();
                    isRun = false;
                    break;
                default: break;
            }
        }

        while(isRun){
            consolePrint.printOptions();
            command = scan.nextInt();
            switch (command){
                case 1:
                    consolePrint.printPlanListHeader();
                    planController.selectMonthPlan(monthMoveCnt);
                    consolePrint.printNextPrePlan();
                    command = scan.nextInt();
                    check = true;
                    while(check){
                        switch (command){
                            case 1:
                                monthMoveCnt--;
                                consolePrint.printPlanListHeader();
                                planController.selectMonthPlan(monthMoveCnt);
                                consolePrint.printNextPrePlan();
                                command = scan.nextInt();
                                break;
                            case 2:
                                monthMoveCnt++;
                                consolePrint.printPlanListHeader();
                                planController.selectMonthPlan(monthMoveCnt);
                                consolePrint.printNextPrePlan();
                                command = scan.nextInt();
                                break;
                            case 3:
                                check = false;
                                break;
                        }
                    }
                    break;
                case 2:
                    consolePrint.printPlanInsertHeader();
                    planController.insertPlan();
                    break;
                case 3:
                    consolePrint.printPlanUpdateHeader();
                    planController.updatePlan();
                    break;
                case 4:
                    consolePrint.printPlanDeleteHeader();
                    planController.deletePlan();
                    break;
                case 5:
                    System.out.println("종료합니다.");
                    userController.save();
                    isRun = false;
                    break;
                default:
                    break;
            }
        }

    }

}
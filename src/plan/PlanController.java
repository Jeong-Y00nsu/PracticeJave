package plan;

import user.User;
import util.Result;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class PlanController {

    private PlanService planService;

    private Scanner scan;
    public PlanController() {
        this.planService = new PlanService();
        this.scan = new Scanner(System.in);
    }

    public void selectMonthPlan(int moveCtn){
        LocalDate pivot = LocalDate.now().plusMonths(moveCtn);
        List<Plan> plans = planService.getPlanByMonth(pivot);

        for(int i=0;i<plans.size();i++){
            System.out.println("["+i+"] "+plans.get(i));
        }
    }

    public Result insertPlan(){
        String title;
        String text;
        String startDt;
        String endDt;

        System.out.println("일정 제목: ");
        title = scan.next();
        System.out.println("일정 메모: ");
        text = scan.next();
        System.out.println("일정 시작 일자 (yyyy-MM-dd HH:mm:ss): ");
        startDt = scan.next();
        System.out.println("일정 마지막 일자 (yyyy-MM-dd HH:mm:ss): ");
        endDt = scan.next();

        if(title == null || startDt == null || endDt == null) return Result.INVALID_PARAM;
        else if(title.equals("") || startDt.equals("") || endDt.equals("")) return Result.INVALID_PARAM;

        Plan plan = new Plan(null,title,text,
                LocalDate.parse(startDt, DateTimeFormatter.ofPattern("yyyy-MMM-dd HH:mm:ss").withLocale(Locale.KOREA)),
                LocalDate.parse(endDt, DateTimeFormatter.ofPattern("yyyy-MMM-dd HH:mm:ss").withLocale(Locale.KOREA)));
        planService.makePlan(plan);

        return Result.OK;
    }

    public Result deletePlan(){
        System.out.println("삭제하고 싶은 일정의 index 번호를 입력해주세요.: ");
        int index = scan.nextInt();
        return planService.deletePlan(index);
    }

    public Result updatePlan(){
        System.out.println("수정하고 싶은 일정의 index 번호를 입력해주세요.: ");
        int index = scan.nextInt();
        System.out.println();

        String title;
        String text;
        String startDt;
        String endDt;

        System.out.println("일정 제목: ");
        title = scan.next();
        System.out.println("일정 메모: ");
        text = scan.next();
        System.out.println("일정 시작 일자 (yyyy-MM-dd HH:mm:ss): ");
        startDt = scan.next();
        System.out.println("일정 마지막 일자 (yyyy-MM-dd HH:mm:ss): ");
        endDt = scan.next();

        Plan plan = new Plan(null,title,text,
                LocalDate.parse(startDt, DateTimeFormatter.ofPattern("yyyy-MMM-dd HH:mm:ss").withLocale(Locale.KOREA)),
                LocalDate.parse(endDt, DateTimeFormatter.ofPattern("yyyy-MMM-dd HH:mm:ss").withLocale(Locale.KOREA)));

        return planService.updatePlan(index, plan);
    }

    public Result save(User user){
        try{
            planService.save(user);
        } catch (Exception e){
            e.printStackTrace();
            return Result.FAIL;
        }
        return Result.OK;
    }
}

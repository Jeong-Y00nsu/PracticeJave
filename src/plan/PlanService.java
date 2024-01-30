package plan;

import user.User;
import util.MakeRandomStr;
import util.Result;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlanService {

    private PlanRepository planRepository;

    private List<Plan> plans;
    private List<Plan> monthPlan;

    public PlanService() {
        this.planRepository = new PlanFileRepository();
        plans = new ArrayList<Plan>();
        monthPlan = new ArrayList<Plan>();
    }

    public void setPlans(User user) throws Exception{
        this.plans = this.planRepository.read(user);
        Collections.sort(this.plans);
    }

    public List<Plan> getPlanByMonth(LocalDate pivot){
        LocalDate firstDay = pivot.withDayOfMonth(1);
        LocalDate lastDay = pivot.withDayOfMonth(pivot.lengthOfMonth());

        for(Plan plan : plans){
            if(plan.getStartDt().isBefore(firstDay)&&plan.getEndDt().isAfter(lastDay)) continue;
            else monthPlan.add(plan);
        }

        return monthPlan;
    }

    public void save(User user) throws Exception{
        this.planRepository.write(this.plans,user);
    }

    private String generatePlanId(){
        return MakeRandomStr.makeRandomPk(10,"yyMMdd");
    }

    public void makePlan(Plan plan){
        plan.setId(generatePlanId());
        this.plans.add(plan);
    }

    public Plan findPlanById(String id){
        for(Plan plan: plans){
            if(plan.getId().equals(id)) return plan;
        }
        return null;
    }
    public Result updatePlan(int index, Plan plan){
        if(index <0 || index >= monthPlan.size()) return Result.INVALID_PARAM;
        Plan prePlan = plans.get(index);
        if(prePlan!=null) {
            if(plan.getTitle()!=null) plans.get(index).setTitle(plan.getTitle());
            if(plan.getText()!=null) plans.get(index).setText(plan.getText());
            if(plan.getStartDt()!=null) plans.get(index).setStartDt(plan.getStartDt());
            if(plan.getEndDt()!=null) plans.get(index).setEndDt(plan.getEndDt());
        }
        return Result.OK;
    }

    public Result deletePlan(int index){
        if(index <0 || index >= monthPlan.size()) return Result.INVALID_PARAM;
        plans.remove(index);
        return Result.OK;
    }
}

package plan;

import user.User;

import java.util.List;

public interface PlanRepository {

    public void write(List<Plan> plans, User user) throws Exception;
    public List<Plan> read(User user) throws Exception;

}

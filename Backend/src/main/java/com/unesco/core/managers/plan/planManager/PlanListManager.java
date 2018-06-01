package com.unesco.core.managers.plan.planManager;

import com.unesco.core.managers.plan.planManager.interfaces.planList.IPlanListManager;
import com.unesco.core.models.plan.PlanModel;
import com.unesco.core.services.plan.planService.IPlanDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class PlanListManager implements IPlanListManager {

    public List<PlanModel> planList;

    public PlanListManager() {
        planList = new ArrayList<>();
    }

    public void Init(List<PlanModel> PlanList) {
        planList = PlanList;
    }

    public List<PlanModel> GetAll() {
        return planList;
    }
}

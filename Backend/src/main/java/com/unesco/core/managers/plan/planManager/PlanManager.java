package com.unesco.core.managers.plan.planManager;

import com.unesco.core.managers.plan.planManager.interfaces.plan.IPlanManager;
import com.unesco.core.models.additional.ResponseStatus;
import com.unesco.core.models.plan.PlanModel;
import com.unesco.core.services.plan.planService.IPlanDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class PlanManager implements IPlanManager {

    PlanModel plan;

    public PlanManager() {
        plan = new PlanModel();
    }

    public void Init(PlanModel Plan) {
        plan = Plan;
    }

    public PlanModel Get() {
        return plan;
    }

}

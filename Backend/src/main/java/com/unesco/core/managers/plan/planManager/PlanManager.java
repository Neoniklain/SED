package com.unesco.core.managers.plan.planManager;

import com.unesco.core.managers.plan.planManager.interfaces.plan.IPlanManager;
import com.unesco.core.dto.plan.PlanDTO;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class PlanManager implements IPlanManager {

    PlanDTO plan;

    public PlanManager() {
        plan = new PlanDTO();
    }

    public void Init(PlanDTO Plan) {
        plan = Plan;
    }

    public PlanDTO Get() {
        return plan;
    }

}

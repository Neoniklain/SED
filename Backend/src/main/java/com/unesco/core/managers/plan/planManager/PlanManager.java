package com.unesco.core.managers.plan.planManager;

import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.enums.StatusTypes;
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

    public void init(PlanDTO Plan) {
        plan = Plan;
    }

    public PlanDTO get() {
        return plan;
    }

    @Override
    public ResponseStatusDTO validate() {
        ResponseStatusDTO result = new ResponseStatusDTO(StatusTypes.OK);
        return result;
    }
}

package com.unesco.core.services.plan.planService;

import com.unesco.core.models.plan.PlanDTO;
import com.unesco.core.models.additional.FilterQueryDTO;
import com.unesco.core.services.IDataService;

import java.util.List;

public interface IPlanDataService extends IDataService<PlanDTO> {
    List<PlanDTO> GetPage(FilterQueryDTO filter);
}

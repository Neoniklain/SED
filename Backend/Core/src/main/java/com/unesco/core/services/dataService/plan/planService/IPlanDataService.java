package com.unesco.core.services.dataService.plan.planService;

import com.unesco.core.dto.plan.PlanDTO;
import com.unesco.core.dto.additional.FilterQueryDTO;
import com.unesco.core.services.dataService.IDataService;

import java.util.List;

public interface IPlanDataService extends IDataService<PlanDTO> {
    List<PlanDTO> getPage(FilterQueryDTO filter);
}

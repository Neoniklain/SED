package com.unesco.core.services.plan.planService;

import com.unesco.core.models.plan.PlanModel;
import com.unesco.core.models.additional.FilterQuery;
import com.unesco.core.services.IDataService;

import java.util.List;

public interface IPlanDataService extends IDataService<PlanModel> {
    List<PlanModel> GetPage(FilterQuery filter);
}

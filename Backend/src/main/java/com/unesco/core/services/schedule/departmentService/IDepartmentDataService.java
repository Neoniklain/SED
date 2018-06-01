package com.unesco.core.services.schedule.departmentService;

import com.unesco.core.models.additional.FilterQuery;
import com.unesco.core.models.plan.DepartmentModel;
import com.unesco.core.services.IDataService;

import java.util.List;


public interface IDepartmentDataService extends IDataService<DepartmentModel> {
    List<DepartmentModel> GetPage(FilterQuery filter);
}

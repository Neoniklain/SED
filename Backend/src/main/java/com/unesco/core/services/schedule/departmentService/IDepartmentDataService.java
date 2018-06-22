package com.unesco.core.services.schedule.departmentService;

import com.unesco.core.models.additional.FilterQueryDTO;
import com.unesco.core.models.plan.DepartmentDTO;
import com.unesco.core.services.IDataService;

import java.util.List;


public interface IDepartmentDataService extends IDataService<DepartmentDTO> {
    List<DepartmentDTO> GetPage(FilterQueryDTO filter);
}

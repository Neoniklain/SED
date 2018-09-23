package com.unesco.core.services.dataService.schedule.departmentService;

import com.unesco.core.dto.additional.FilterQueryDTO;
import com.unesco.core.dto.additional.PageResultDTO;
import com.unesco.core.dto.plan.DepartmentDTO;
import com.unesco.core.services.dataService.IDataService;


public interface IDepartmentDataService extends IDataService<DepartmentDTO> {
    PageResultDTO<DepartmentDTO> getPage(FilterQueryDTO filter);
    DepartmentDTO getByName(String name);
}

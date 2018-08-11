package com.unesco.core.services.dataService.schedule.instituteService;

import com.unesco.core.dto.additional.FilterQueryDTO;
import com.unesco.core.dto.shedule.InstituteDTO;
import com.unesco.core.services.dataService.IDataService;

import java.util.List;

public interface IInstituteDataService extends IDataService<InstituteDTO> {
    List<InstituteDTO> getPage(FilterQueryDTO filter);
    InstituteDTO getByName(String name);
}

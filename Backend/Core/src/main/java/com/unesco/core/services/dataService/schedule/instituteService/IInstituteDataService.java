package com.unesco.core.services.dataService.schedule.instituteService;

import com.unesco.core.dto.additional.FilterQueryDTO;
import com.unesco.core.dto.additional.PageResultDTO;
import com.unesco.core.dto.shedule.InstituteDTO;
import com.unesco.core.services.dataService.IDataService;

public interface IInstituteDataService extends IDataService<InstituteDTO> {
    PageResultDTO<InstituteDTO> getPage(FilterQueryDTO filter);
    InstituteDTO getByName(String name);
}

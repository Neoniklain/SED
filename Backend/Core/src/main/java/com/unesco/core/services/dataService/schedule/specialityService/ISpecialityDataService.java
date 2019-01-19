package com.unesco.core.services.dataService.schedule.specialityService;

import com.unesco.core.dto.additional.FilterQueryDTO;
import com.unesco.core.dto.additional.PageResultDTO;
import com.unesco.core.dto.shedule.SpecialityDTO;
import com.unesco.core.services.dataService.IDataService;

public interface ISpecialityDataService  extends IDataService<SpecialityDTO> {
    PageResultDTO<SpecialityDTO> getPage(FilterQueryDTO filter);
}

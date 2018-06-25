package com.unesco.core.services.schedule.instituteService;

import com.unesco.core.dto.additional.FilterQueryDTO;
import com.unesco.core.dto.shedule.InstituteDTO;
import com.unesco.core.services.IDataService;

import java.util.List;

public interface IInstituteDataService extends IDataService<InstituteDTO> {
    List<InstituteDTO> GetPage(FilterQueryDTO filter);
    InstituteDTO GetByName(String name);
}

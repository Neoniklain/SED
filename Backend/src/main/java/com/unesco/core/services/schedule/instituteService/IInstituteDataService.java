package com.unesco.core.services.schedule.instituteService;

import com.unesco.core.models.additional.FilterQueryDTO;
import com.unesco.core.models.shedule.InstituteDTO;
import com.unesco.core.services.IDataService;

import java.util.List;

public interface IInstituteDataService extends IDataService<InstituteDTO> {
    List<InstituteDTO> GetPage(FilterQueryDTO filter);
    InstituteDTO GetByName(String name);
}

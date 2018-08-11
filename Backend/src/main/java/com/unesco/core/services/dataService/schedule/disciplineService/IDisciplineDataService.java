package com.unesco.core.services.dataService.schedule.disciplineService;

import com.unesco.core.dto.shedule.DisciplineDTO;
import com.unesco.core.dto.additional.FilterQueryDTO;
import com.unesco.core.services.dataService.IDataService;

import java.util.List;

public interface IDisciplineDataService extends IDataService<DisciplineDTO> {
    List<DisciplineDTO> getPage(FilterQueryDTO filter);
    DisciplineDTO getByName(String name);
}

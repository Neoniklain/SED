package com.unesco.core.services.dataService.schedule.disciplineService;

import com.unesco.core.dto.additional.FilterQueryDTO;
import com.unesco.core.dto.additional.PageResultDTO;
import com.unesco.core.dto.shedule.DisciplineDTO;
import com.unesco.core.services.dataService.IDataService;

public interface IDisciplineDataService extends IDataService<DisciplineDTO> {
    PageResultDTO<DisciplineDTO> getPage(FilterQueryDTO filter);
    DisciplineDTO getByName(String name);
}

package com.unesco.core.services.schedule.disciplineService;

import com.unesco.core.dto.shedule.DisciplineDTO;
import com.unesco.core.dto.additional.FilterQueryDTO;
import com.unesco.core.services.IDataService;

import java.util.List;

public interface IDisciplineDataService extends IDataService<DisciplineDTO> {
    List<DisciplineDTO> GetPage(FilterQueryDTO filter);
    DisciplineDTO GetByName(String name);
}

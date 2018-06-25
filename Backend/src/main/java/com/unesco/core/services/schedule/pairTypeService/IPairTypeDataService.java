package com.unesco.core.services.schedule.pairTypeService;

import com.unesco.core.dto.additional.FilterQueryDTO;
import com.unesco.core.dto.shedule.PairTypeDTO;
import com.unesco.core.services.IDataService;

import java.util.List;

public interface IPairTypeDataService extends IDataService<PairTypeDTO> {
    PairTypeDTO GetByType(String name);
    List<PairTypeDTO> GetPage(FilterQueryDTO filter);
}

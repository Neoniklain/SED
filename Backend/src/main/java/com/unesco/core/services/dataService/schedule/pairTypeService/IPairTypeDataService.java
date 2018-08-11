package com.unesco.core.services.dataService.schedule.pairTypeService;

import com.unesco.core.dto.additional.FilterQueryDTO;
import com.unesco.core.dto.shedule.PairTypeDTO;
import com.unesco.core.services.dataService.IDataService;

import java.util.List;

public interface IPairTypeDataService extends IDataService<PairTypeDTO> {
    PairTypeDTO getByType(String name);
    List<PairTypeDTO> getPage(FilterQueryDTO filter);
}

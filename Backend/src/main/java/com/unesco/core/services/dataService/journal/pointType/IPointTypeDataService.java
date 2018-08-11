package com.unesco.core.services.dataService.journal.pointType;

import com.unesco.core.dto.journal.PointTypeDTO;
import com.unesco.core.services.dataService.IDataService;


public interface IPointTypeDataService extends IDataService<PointTypeDTO> {
    PointTypeDTO findByName(String name);
}

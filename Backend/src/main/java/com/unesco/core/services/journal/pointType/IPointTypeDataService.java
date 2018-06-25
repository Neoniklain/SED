package com.unesco.core.services.journal.pointType;

import com.unesco.core.dto.journal.PointTypeDTO;
import com.unesco.core.services.IDataService;


public interface IPointTypeDataService extends IDataService<PointTypeDTO> {
    PointTypeDTO FindByName(String name);
}

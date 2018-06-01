package com.unesco.core.services.journal.pointType;

import com.unesco.core.models.journal.PointTypeModel;
import com.unesco.core.services.IDataService;


public interface IPointTypeDataService extends IDataService<PointTypeModel> {
    PointTypeModel FindByName(String name);
}

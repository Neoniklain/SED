package com.unesco.core.services.journal.point;

import com.unesco.core.models.journal.PointModel;
import com.unesco.core.services.IDataService;

import java.util.Date;
import java.util.List;

public interface IPointDataService extends IDataService<PointModel> {

    List<PointModel> GetByStudentAndPair(long studentId, long lessonId);
    PointModel GetByStudentAndDateAndTypeAndPair(long studentId, Date date, long typeId, long pairId);

}

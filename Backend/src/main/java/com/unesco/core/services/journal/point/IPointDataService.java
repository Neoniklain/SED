package com.unesco.core.services.journal.point;

import com.unesco.core.dto.journal.PointDTO;
import com.unesco.core.services.IDataService;

import java.util.Date;
import java.util.List;

public interface IPointDataService extends IDataService<PointDTO> {

    List<PointDTO> GetByStudentAndPair(long studentId, long lessonId);
    List<PointDTO> GetByLesson(long lessonId);
    PointDTO GetByStudentAndDateAndTypeAndPair(long studentId, Date date, long typeId, long pairId);

}

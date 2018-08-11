package com.unesco.core.services.dataService.journal.point;

import com.unesco.core.dto.journal.PointDTO;
import com.unesco.core.services.dataService.IDataService;

import java.util.Date;
import java.util.List;

public interface IPointDataService extends IDataService<PointDTO> {

    List<PointDTO> getByStudentAndPair(long studentId, long lessonId);
    List<PointDTO> getByLesson(long lessonId);
    PointDTO getByStudentAndDateAndTypeAndPair(long studentId, Date date, long typeId, long pairId);

}

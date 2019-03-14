package com.unesco.core.services.dataService.schedule.lessonService;

import com.unesco.core.dto.shedule.LessonDTO;
import com.unesco.core.services.dataService.IDataService;

import java.util.List;

public interface ILessonDataService extends IDataService<LessonDTO> {
    LessonDTO getDisciplineIdAndGroupIdAndProfessorIdAndPeriodId(long disciplineId, long groupId, long professorId, long longPeriodId);
    List<LessonDTO> getByProfessorId(long professorId, int semester, int year);
    List<LessonDTO> getByGroupId(long groupId, int semester, int year);
}

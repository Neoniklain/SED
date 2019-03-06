package com.unesco.core.services.dataService.schedule.lessonService;

import com.unesco.core.dto.shedule.LessonDTO;
import com.unesco.core.services.dataService.IDataService;

import java.util.List;

public interface ILessonDataService extends IDataService<LessonDTO> {
    LessonDTO getDisciplineIdAndGroupIdAndProfessorId(long disciplineId, long groupId, long professorId);
    List<LessonDTO> getByProfessorId(long professorId, int semester, int year);
    List<LessonDTO> getByGroupId(long groupId, int semester, int year);
}

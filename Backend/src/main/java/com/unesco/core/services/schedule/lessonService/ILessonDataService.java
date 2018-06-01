package com.unesco.core.services.schedule.lessonService;

import com.unesco.core.models.shedule.LessonModel;
import com.unesco.core.services.IDataService;

public interface ILessonDataService extends IDataService<LessonModel> {
    LessonModel GetDisciplineIdAndGroupIdAndProfessorId(long disciplineId, long groupId, long professorId);
}

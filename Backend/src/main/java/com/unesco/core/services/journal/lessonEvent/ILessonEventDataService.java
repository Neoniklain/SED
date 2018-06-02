package com.unesco.core.services.journal.lessonEvent;

import com.unesco.core.models.journal.LessonEventModel;
import com.unesco.core.services.IDataService;

import java.util.List;

public interface ILessonEventDataService extends IDataService<LessonEventModel> {
    List<LessonEventModel> GetByLesson(long lessonId);
}

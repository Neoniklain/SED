package com.unesco.core.services.journal.lessonEvent;

import com.unesco.core.dto.journal.LessonEventDTO;
import com.unesco.core.services.IDataService;

import java.util.List;

public interface ILessonEventDataService extends IDataService<LessonEventDTO> {
    List<LessonEventDTO> GetByLesson(long lessonId);
}

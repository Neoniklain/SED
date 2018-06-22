package com.unesco.core.managers.journal.lessonEvent.interfaces.lessonEventList;

import com.unesco.core.managers.IListManager;
import com.unesco.core.models.journal.LessonEventDTO;
import com.unesco.core.models.shedule.LessonDTO;

public interface ILessonEventListManager extends IListManager<LessonEventDTO> {
    void ApplayFilter(LessonDTO lesson);
}

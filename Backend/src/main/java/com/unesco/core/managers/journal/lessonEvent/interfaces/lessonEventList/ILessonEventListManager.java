package com.unesco.core.managers.journal.lessonEvent.interfaces.lessonEventList;

import com.unesco.core.managers.IListManager;
import com.unesco.core.models.journal.LessonEventModel;
import com.unesco.core.models.shedule.LessonModel;

public interface ILessonEventListManager extends IListManager<LessonEventModel> {
    void ApplayFilter(LessonModel lesson);
}

package com.unesco.core.managers.journal.lessonEvent.interfaces.lessonEventList;

import com.unesco.core.managers.IListManager;
import com.unesco.core.models.journal.PairEventModel;
import com.unesco.core.models.shedule.LessonModel;

public interface IPairEventListManager extends IListManager<PairEventModel> {
    void ApplayFilter(LessonModel lesson);
}

package com.unesco.core.managers.journal.lessonEvent;

import com.unesco.core.managers.journal.lessonEvent.interfaces.lessonEvent.IPairEventManager;
import com.unesco.core.models.journal.LessonEventModel;
import com.unesco.core.services.journal.lessonEvent.ILessonEventDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class PairEventManager implements IPairEventManager {


    @Autowired
    public ILessonEventDataService dataService;

    public LessonEventModel lessonEvent;

    public PairEventManager() {
        lessonEvent = new LessonEventModel();
    }

    public void Init(LessonEventModel Professor) {
        lessonEvent = Professor;
    }

    public LessonEventModel Get() {
        return lessonEvent;
    }

}

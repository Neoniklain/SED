package com.unesco.core.managers.journal.lessonEvent;

import com.unesco.core.managers.journal.lessonEvent.interfaces.lessonEvent.ILessonEventManager;
import com.unesco.core.models.additional.ResponseStatus;
import com.unesco.core.models.journal.LessonEventModel;
import com.unesco.core.services.journal.lessonEvent.ILessonEventDataService;
import com.unesco.core.utils.StatusTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class LessonEventManager implements ILessonEventManager {

    @Autowired
    public ILessonEventDataService dataService;

    public LessonEventModel lessonEvent;

    public LessonEventManager() {
        lessonEvent = new LessonEventModel();
    }

    public void Init(LessonEventModel Professor) {
        lessonEvent = Professor;
    }

    public LessonEventModel Get() {
        return lessonEvent;
    }

    public ResponseStatus Validate() {
        ResponseStatus responseStatus = new ResponseStatus();
        responseStatus.setStatus(StatusTypes.OK);
        if (lessonEvent.getType().getName().equals("")) {
            responseStatus.setStatus(StatusTypes.ERROR);
            responseStatus.addErrors("Не указан тип события");
        }
        if (lessonEvent.getDate() == null) {
            responseStatus.setStatus(StatusTypes.ERROR);
            responseStatus.addErrors("Не указана дата события");
        }
        if (lessonEvent.getLesson().getId() == 0) {
            responseStatus.setStatus(StatusTypes.ERROR);
            responseStatus.addErrors("Не указан урок");
        }
        return responseStatus;
    }
}

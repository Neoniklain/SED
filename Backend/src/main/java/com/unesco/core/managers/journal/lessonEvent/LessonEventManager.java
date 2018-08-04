package com.unesco.core.managers.journal.lessonEvent;

import com.unesco.core.managers.journal.lessonEvent.interfaces.lessonEvent.ILessonEventManager;
import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.journal.LessonEventDTO;
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

    public LessonEventDTO lessonEvent;

    public LessonEventManager() {
        lessonEvent = new LessonEventDTO();
    }

    public void Init(LessonEventDTO Professor) {
        lessonEvent = Professor;
    }

    public LessonEventDTO Get() {
        return lessonEvent;
    }

    public ResponseStatusDTO Validate() {
        ResponseStatusDTO responseStatusDTO = new ResponseStatusDTO();
        responseStatusDTO.setStatus(StatusTypes.OK);
        if (lessonEvent.getType() == null || lessonEvent.getType().getName().equals("")) {
            responseStatusDTO.setStatus(StatusTypes.ERROR);
            responseStatusDTO.addErrors("Не указан тип события");
        }
        if (lessonEvent.getDate() == null) {
            responseStatusDTO.setStatus(StatusTypes.ERROR);
            responseStatusDTO.addErrors("Не указана дата события");
        }
        if (lessonEvent.getLesson() == null || lessonEvent.getLesson().getId() == 0) {
            responseStatusDTO.setStatus(StatusTypes.ERROR);
            responseStatusDTO.addErrors("Не указан урок");
        }
        return responseStatusDTO;
    }
}

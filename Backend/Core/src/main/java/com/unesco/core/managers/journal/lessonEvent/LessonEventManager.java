package com.unesco.core.managers.journal.lessonEvent;

import com.unesco.core.managers.journal.lessonEvent.interfaces.lessonEvent.ILessonEventManager;
import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.journal.LessonEventDTO;
import com.unesco.core.services.dataService.journal.lessonEvent.ILessonEventDataService;
import com.unesco.core.dto.enums.StatusTypes;
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

    public void init(LessonEventDTO Professor) {
        lessonEvent = Professor;
    }

    public LessonEventDTO get() {
        return lessonEvent;
    }

    public ResponseStatusDTO validate() {
        ResponseStatusDTO responseStatusDTO = new ResponseStatusDTO();
        responseStatusDTO.setStatus(StatusTypes.OK);
        if (lessonEvent.getType() == null || lessonEvent.getType().getName().equals("")) {
            responseStatusDTO.setStatus(StatusTypes.ERROR);
            responseStatusDTO.addErrors("Не указан тип события");
        }
        if (lessonEvent.getLesson() == null || lessonEvent.getLesson().getId() == 0) {
            responseStatusDTO.setStatus(StatusTypes.ERROR);
            responseStatusDTO.addErrors("Не указан урок");
        }
        if (lessonEvent.getMaxValue() < 0) {
            responseStatusDTO.setStatus(StatusTypes.ERROR);
            responseStatusDTO.addErrors("Не dверное значение балла.");
        }
        return responseStatusDTO;
    }
}

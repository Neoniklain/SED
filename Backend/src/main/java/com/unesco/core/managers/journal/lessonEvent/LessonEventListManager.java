package com.unesco.core.managers.journal.lessonEvent;

import com.unesco.core.managers.journal.lessonEvent.interfaces.lessonEventList.ILessonEventListManager;
import com.unesco.core.dto.journal.LessonEventDTO;
import com.unesco.core.dto.shedule.LessonDTO;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class LessonEventListManager implements ILessonEventListManager {

    public List<LessonEventDTO> lessonEventList;

    public LessonEventListManager() {
        lessonEventList = new ArrayList<>();
    }

    public void Init(List<LessonEventDTO> LessonEventist) {
        lessonEventList = LessonEventist;
    }

    public List<LessonEventDTO> GetAll() {
        return lessonEventList;
    }

    public void ApplayFilter(LessonDTO lesson) {
        List<LessonEventDTO> result = new ArrayList<>();
        for (LessonEventDTO p: lessonEventList) {
            if(p.getLesson().getDiscipline().getId() == lesson.getDiscipline().getId() &&
                p.getLesson().getGroup().getId() == lesson.getGroup().getId() &&
                p.getLesson().getProfessor().getId() == lesson.getProfessor().getId())
                result.add(p);
        }
        lessonEventList = result;
    }

}

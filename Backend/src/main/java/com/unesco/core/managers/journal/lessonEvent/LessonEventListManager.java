package com.unesco.core.managers.journal.lessonEvent;

import com.unesco.core.managers.journal.lessonEvent.interfaces.lessonEventList.ILessonEventListManager;
import com.unesco.core.models.journal.LessonEventModel;
import com.unesco.core.models.shedule.LessonModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class LessonEventListManager implements ILessonEventListManager {

    public List<LessonEventModel> lessonEventList;

    public LessonEventListManager() {
        lessonEventList = new ArrayList<>();
    }

    public void Init(List<LessonEventModel> LessonEventist) {
        lessonEventList = LessonEventist;
    }

    public List<LessonEventModel> GetAll() {
        return lessonEventList;
    }

    public void ApplayFilter(LessonModel lesson) {
        List<LessonEventModel> result = new ArrayList<>();
        for (LessonEventModel p: lessonEventList) {
            if(p.getLesson().getDiscipline().getId() == lesson.getDiscipline().getId() &&
                p.getLesson().getGroup().getId() == lesson.getGroup().getId() &&
                p.getLesson().getProfessor().getId() == lesson.getProfessor().getId())
                result.add(p);
        }
        lessonEventList = result;
    }

}

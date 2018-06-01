package com.unesco.core.managers.journal.lessonEvent;

import com.unesco.core.managers.journal.lessonEvent.interfaces.lessonEventList.IPairEventListManager;
import com.unesco.core.models.journal.PairEventModel;
import com.unesco.core.models.shedule.LessonModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class PairEventListManager implements IPairEventListManager {

    public List<PairEventModel> lessonEventList;

    public PairEventListManager() {
        lessonEventList = new ArrayList<>();
    }

    public void Init(List<PairEventModel> LessonEventist) {
        lessonEventList = LessonEventist;
    }

    public List<PairEventModel> GetAll() {
        return lessonEventList;
    }

    public void ApplayFilter(LessonModel lesson) {
        List<PairEventModel> result = new ArrayList<>();
        for (PairEventModel p: lessonEventList) {
            if(p.getPair().getDiscipline().getId() == lesson.getDiscipline().getId() &&
                p.getPair().getGroup().getId() == lesson.getGroup().getId() &&
                p.getPair().getProfessor().getId() == lesson.getProfessor().getId())
                result.add(p);
        }
        lessonEventList = result;
    }

}

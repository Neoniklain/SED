package com.unesco.core.managers.schedule.disciplineManager;

import com.unesco.core.managers.schedule.disciplineManager.interfaces.discipline.IDisciplineManager;
import com.unesco.core.models.additional.ResponseStatus;
import com.unesco.core.models.shedule.DisciplineModel;
import com.unesco.core.utils.StatusTypes;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class DisciplineManager implements IDisciplineManager {

    DisciplineModel discipline;

    public DisciplineManager() {
        discipline = new DisciplineModel();
    }

    public void Init(DisciplineModel Discipline) {
        discipline = Discipline;
    }

    public DisciplineModel Get() {
        return discipline;
    }

    public ResponseStatus Validate() {
        ResponseStatus responseStatus = new ResponseStatus();
        responseStatus.setStatus(StatusTypes.OK);
        if (discipline.getName().equals("")) {
            responseStatus.setStatus(StatusTypes.ERROR);
            responseStatus.addErrors("Не указано название дисциплины");
        }
        if (discipline.getFieldOfKnowledge().getId() == 0) {
            responseStatus.setStatus(StatusTypes.ERROR);
            responseStatus.addErrors("Не указан раздел знаний");
        }
        return responseStatus;
    }

}

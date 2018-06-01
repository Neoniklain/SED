package com.unesco.core.managers.schedule.disciplineManager;

import com.unesco.core.managers.schedule.disciplineManager.interfaces.discipline.IDisciplineManager;
import com.unesco.core.models.additional.ResponseStatus;
import com.unesco.core.models.shedule.DisciplineModel;
import com.unesco.core.services.schedule.disciplineService.IDisciplineDataService;
import org.springframework.beans.factory.annotation.Autowired;
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

}

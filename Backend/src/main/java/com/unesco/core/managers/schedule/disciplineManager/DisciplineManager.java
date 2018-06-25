package com.unesco.core.managers.schedule.disciplineManager;

import com.unesco.core.managers.schedule.disciplineManager.interfaces.discipline.IDisciplineManager;
import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.shedule.DisciplineDTO;
import com.unesco.core.utils.StatusTypes;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class DisciplineManager implements IDisciplineManager {

    DisciplineDTO discipline;

    public DisciplineManager() {
        discipline = new DisciplineDTO();
    }

    public void Init(DisciplineDTO Discipline) {
        discipline = Discipline;
    }

    public DisciplineDTO Get() {
        return discipline;
    }

    public ResponseStatusDTO Validate() {
        ResponseStatusDTO responseStatusDTO = new ResponseStatusDTO();
        responseStatusDTO.setStatus(StatusTypes.OK);
        if (discipline.getName().equals("")) {
            responseStatusDTO.setStatus(StatusTypes.ERROR);
            responseStatusDTO.addErrors("Не указано название дисциплины");
        }
        if (discipline.getFieldOfKnowledge().getId() == 0) {
            responseStatusDTO.setStatus(StatusTypes.ERROR);
            responseStatusDTO.addErrors("Не указан раздел знаний");
        }
        return responseStatusDTO;
    }

}

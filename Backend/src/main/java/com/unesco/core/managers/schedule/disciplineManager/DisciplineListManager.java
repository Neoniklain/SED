package com.unesco.core.managers.schedule.disciplineManager;

import com.unesco.core.managers.schedule.disciplineManager.interfaces.disciplineList.IDisciplineListManager;
import com.unesco.core.dto.shedule.DisciplineDTO;
import com.unesco.core.services.schedule.disciplineService.IDisciplineDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class DisciplineListManager implements IDisciplineListManager {

    @Autowired
    public IDisciplineDataService dataService;

    public List<DisciplineDTO> disciplineList;

    public DisciplineListManager() {
        disciplineList = new ArrayList<>();
    }

    public void Init(List<DisciplineDTO> DisciplineList) {
        disciplineList = DisciplineList;
    }

    public List<DisciplineDTO> GetAll() {
        return disciplineList;
    }
}

package com.unesco.core.managers.schedule.pairManager.interfaces.pairList;

import com.unesco.core.managers.IListManager;
import com.unesco.core.models.account.ProfessorDTO;
import com.unesco.core.models.plan.DepartmentDTO;
import com.unesco.core.models.shedule.DisciplineDTO;
import com.unesco.core.models.shedule.GroupDTO;
import com.unesco.core.models.shedule.PairDTO;

public interface IPairListManager extends IListManager<PairDTO> {

    void ApplayFilter(ProfessorDTO professor);
    void ApplayFilter(GroupDTO group);
    void ApplayFilter(DisciplineDTO discipline);
    void ApplayFilter(DepartmentDTO department);

}

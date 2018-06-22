package com.unesco.core.managers.schedule.sheduleManager.interfaces.shedule;

import com.unesco.core.models.SheduleDTO;
import com.unesco.core.models.account.ProfessorDTO;
import com.unesco.core.models.plan.DepartmentDTO;
import com.unesco.core.models.shedule.GroupDTO;
import com.unesco.core.models.shedule.PairDTO;

import java.util.List;

public interface ISheduleManager {
    void Init(List<PairDTO> pairList, ProfessorDTO professor);
    void Init(List<PairDTO> pairList, DepartmentDTO department);
    void Init(List<PairDTO> pairList, GroupDTO groupModel);

    SheduleDTO Get();
}

package com.unesco.core.managers.schedule.sheduleManager.interfaces.shedule;

import com.unesco.core.dto.SheduleDTO;
import com.unesco.core.dto.account.ProfessorDTO;
import com.unesco.core.dto.plan.DepartmentDTO;
import com.unesco.core.dto.shedule.GroupDTO;
import com.unesco.core.dto.shedule.PairDTO;

import java.util.List;

public interface ISheduleManager {
    void Init(List<PairDTO> pairList, ProfessorDTO professor);
    void Init(List<PairDTO> pairList, DepartmentDTO department);
    void Init(List<PairDTO> pairList, GroupDTO groupModel);

    SheduleDTO Get();
}

package com.unesco.core.managers.schedule.sheduleDepartmentManager.sheduleDepartment;

import com.unesco.core.models.account.ProfessorDTO;
import com.unesco.core.models.plan.DepartmentDTO;
import com.unesco.core.models.shedule.DepartmentSheduleDTO;
import com.unesco.core.models.shedule.PairDTO;

import java.util.List;

public interface ISheduleDepartmentManager {

    void Init(List<PairDTO> pairList, List<ProfessorDTO> professorList, DepartmentDTO department);

    DepartmentSheduleDTO Get();
}

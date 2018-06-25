package com.unesco.core.managers.schedule.sheduleDepartmentManager.sheduleDepartment;

import com.unesco.core.dto.account.ProfessorDTO;
import com.unesco.core.dto.plan.DepartmentDTO;
import com.unesco.core.dto.shedule.DepartmentSheduleDTO;
import com.unesco.core.dto.shedule.PairDTO;

import java.util.List;

public interface ISheduleDepartmentManager {

    void Init(List<PairDTO> pairList, List<ProfessorDTO> professorList, DepartmentDTO department);

    DepartmentSheduleDTO Get();
}

package com.unesco.core.managers.schedule.sheduleDepartmentManager.sheduleDepartment;

import com.unesco.core.models.account.ProfessorModel;
import com.unesco.core.models.plan.DepartmentModel;
import com.unesco.core.models.shedule.DepartmentSheduleModel;
import com.unesco.core.models.shedule.PairModel;

import java.util.List;

public interface ISheduleDepartmentManager {

    void Init(List<PairModel> pairList, List<ProfessorModel> professorList, DepartmentModel department);

    DepartmentSheduleModel Get();
}

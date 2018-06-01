package com.unesco.core.managers.schedule.sheduleManager.interfaces.shedule;

import com.unesco.core.models.SheduleModel;
import com.unesco.core.models.account.ProfessorModel;
import com.unesco.core.models.plan.DepartmentModel;
import com.unesco.core.models.shedule.GroupModel;
import com.unesco.core.models.shedule.PairModel;

import java.util.List;

public interface ISheduleManager {
    void Init(List<PairModel> pairList, ProfessorModel professor);
    void Init(List<PairModel> pairList, DepartmentModel department);
    void Init(List<PairModel> pairList, GroupModel groupModel);

    SheduleModel Get();
}

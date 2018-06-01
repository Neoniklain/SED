package com.unesco.core.managers.schedule.pairManager.interfaces.pairList;

import com.unesco.core.managers.IListManager;
import com.unesco.core.models.account.ProfessorModel;
import com.unesco.core.models.plan.DepartmentModel;
import com.unesco.core.models.shedule.DisciplineModel;
import com.unesco.core.models.shedule.GroupModel;
import com.unesco.core.models.shedule.PairModel;

public interface IPairListManager extends IListManager<PairModel> {

    void ApplayFilter(ProfessorModel professor);
    void ApplayFilter(GroupModel group);
    void ApplayFilter(DisciplineModel discipline);
    void ApplayFilter(DepartmentModel department);

}

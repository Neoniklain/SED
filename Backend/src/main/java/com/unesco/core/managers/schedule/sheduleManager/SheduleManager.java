package com.unesco.core.managers.schedule.sheduleManager;

import com.unesco.core.managers.schedule.pairManager.interfaces.pairList.IPairListManager;
import com.unesco.core.managers.schedule.sheduleManager.interfaces.shedule.ISheduleManager;
import com.unesco.core.models.SheduleModel;
import com.unesco.core.models.account.ProfessorModel;
import com.unesco.core.models.plan.DepartmentModel;
import com.unesco.core.models.shedule.DepartmentSheduleModel;
import com.unesco.core.models.shedule.GroupModel;
import com.unesco.core.models.shedule.PairModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope("prototype")
public class SheduleManager implements ISheduleManager {

    SheduleModel shedule;

    DepartmentSheduleModel departmentShedule;

    @Autowired
    private IPairListManager pairListManager;

    public SheduleManager() {
        shedule = new SheduleModel();
    }

    public void Init(List<PairModel> pairList, ProfessorModel professor) {
        pairListManager.Init(pairList);
        pairListManager.ApplayFilter(professor);
        shedule.setPairs(pairListManager.GetAll());
    }

    public void Init(List<PairModel> pairList, DepartmentModel department) {
        pairListManager.Init(pairList);
        pairListManager.ApplayFilter(department);
        shedule.setPairs(pairListManager.GetAll());
    }

    public void Init(List<PairModel> pairList, GroupModel groupModel) {
        pairListManager.Init(pairList);
        pairListManager.ApplayFilter(groupModel);
        shedule.setPairs(pairListManager.GetAll());
    }

    public SheduleModel Get() {
        return shedule;
    }

}

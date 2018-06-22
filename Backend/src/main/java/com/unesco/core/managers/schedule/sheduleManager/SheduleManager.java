package com.unesco.core.managers.schedule.sheduleManager;

import com.unesco.core.managers.schedule.pairManager.interfaces.pairList.IPairListManager;
import com.unesco.core.managers.schedule.sheduleManager.interfaces.shedule.ISheduleManager;
import com.unesco.core.models.SheduleDTO;
import com.unesco.core.models.account.ProfessorDTO;
import com.unesco.core.models.plan.DepartmentDTO;
import com.unesco.core.models.shedule.DepartmentSheduleDTO;
import com.unesco.core.models.shedule.GroupDTO;
import com.unesco.core.models.shedule.PairDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope("prototype")
public class SheduleManager implements ISheduleManager {

    SheduleDTO shedule;

    DepartmentSheduleDTO departmentShedule;

    @Autowired
    private IPairListManager pairListManager;

    public SheduleManager() {
        shedule = new SheduleDTO();
    }

    public void Init(List<PairDTO> pairList, ProfessorDTO professor) {
        pairListManager.Init(pairList);
        pairListManager.ApplayFilter(professor);
        shedule.setPairs(pairListManager.GetAll());
    }

    public void Init(List<PairDTO> pairList, DepartmentDTO department) {
        pairListManager.Init(pairList);
        pairListManager.ApplayFilter(department);
        shedule.setPairs(pairListManager.GetAll());
    }

    public void Init(List<PairDTO> pairList, GroupDTO groupModel) {
        pairListManager.Init(pairList);
        pairListManager.ApplayFilter(groupModel);
        shedule.setPairs(pairListManager.GetAll());
    }

    public SheduleDTO Get() {
        return shedule;
    }

}

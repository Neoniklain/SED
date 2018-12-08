package com.unesco.core.managers.schedule.sheduleManager;

import com.unesco.core.managers.schedule.pairManager.interfaces.pairList.IPairListManager;
import com.unesco.core.managers.schedule.sheduleManager.interfaces.shedule.ISheduleManager;
import com.unesco.core.dto.shedule.SheduleDTO;
import com.unesco.core.dto.account.ProfessorDTO;
import com.unesco.core.dto.plan.DepartmentDTO;
import com.unesco.core.dto.shedule.DepartmentSheduleDTO;
import com.unesco.core.dto.shedule.GroupDTO;
import com.unesco.core.dto.shedule.PairDTO;
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

    public void init(List<PairDTO> pairList, ProfessorDTO professor) {
        pairListManager.init(pairList);
        pairListManager.ApplayFilter(professor);
        shedule.setPairs(pairListManager.getAll());
    }

    public void init(List<PairDTO> pairList, DepartmentDTO department) {
        pairListManager.init(pairList);
        pairListManager.ApplayFilter(department);
        shedule.setPairs(pairListManager.getAll());
    }

    public void init(List<PairDTO> pairList, GroupDTO groupModel) {
        pairListManager.init(pairList);
        pairListManager.ApplayFilter(groupModel);
        shedule.setPairs(pairListManager.getAll());
    }

    public SheduleDTO get() {
        return shedule;
    }

}

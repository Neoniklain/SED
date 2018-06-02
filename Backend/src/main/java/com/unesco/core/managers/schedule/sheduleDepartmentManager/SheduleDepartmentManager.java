package com.unesco.core.managers.schedule.sheduleDepartmentManager;

import com.unesco.core.managers.schedule.pairManager.interfaces.pairList.IPairListManager;
import com.unesco.core.managers.schedule.sheduleDepartmentManager.sheduleDepartment.ISheduleDepartmentManager;
import com.unesco.core.models.account.ProfessorModel;
import com.unesco.core.models.plan.DepartmentModel;
import com.unesco.core.models.shedule.DepartmentSheduleModel;
import com.unesco.core.models.shedule.PairModel;
import com.unesco.core.models.shedule.SheduleProfessorLineModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@Scope("prototype")
public class SheduleDepartmentManager implements ISheduleDepartmentManager {

    private DepartmentSheduleModel departmentShedule;

    @Autowired
    private IPairListManager pairListManager;

    public SheduleDepartmentManager() {
        departmentShedule = new DepartmentSheduleModel();
    }

    public void Init(List<PairModel> pairList, List<ProfessorModel> professorList, DepartmentModel department) {

        pairListManager.Init(pairList);
        pairListManager.ApplayFilter(department);

        List<SheduleProfessorLineModel> line = new ArrayList<>();

        for (ProfessorModel p: professorList) {
            List<PairModel> collect = pairList.stream().filter(o -> o.getLesson().getProfessor().getId() == p.getId()).collect(Collectors.toList());
            SheduleProfessorLineModel sheduleLineModel = new SheduleProfessorLineModel();
            sheduleLineModel.setPairs(collect);
            sheduleLineModel.setProfessor(p);
            line.add(sheduleLineModel);
        }

        departmentShedule.setLines(line);
    }

    public DepartmentSheduleModel Get() {
        return departmentShedule;
    }

}

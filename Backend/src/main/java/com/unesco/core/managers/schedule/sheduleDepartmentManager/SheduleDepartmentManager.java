package com.unesco.core.managers.schedule.sheduleDepartmentManager;

import com.unesco.core.managers.schedule.pairManager.interfaces.pairList.IPairListManager;
import com.unesco.core.managers.schedule.sheduleDepartmentManager.sheduleDepartment.ISheduleDepartmentManager;
import com.unesco.core.dto.account.ProfessorDTO;
import com.unesco.core.dto.plan.DepartmentDTO;
import com.unesco.core.dto.shedule.DepartmentSheduleDTO;
import com.unesco.core.dto.shedule.PairDTO;
import com.unesco.core.dto.shedule.SheduleProfessorLineDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@Scope("prototype")
public class SheduleDepartmentManager implements ISheduleDepartmentManager {

    private DepartmentSheduleDTO departmentShedule;

    @Autowired
    private IPairListManager pairListManager;

    public SheduleDepartmentManager() {
        departmentShedule = new DepartmentSheduleDTO();
    }

    public void Init(List<PairDTO> pairList, List<ProfessorDTO> professorList, DepartmentDTO department) {

        pairListManager.Init(pairList);
        pairListManager.ApplayFilter(department);

        List<SheduleProfessorLineDTO> line = new ArrayList<>();

        for (ProfessorDTO p: professorList) {
            List<PairDTO> collect = pairList.stream().filter(o -> o.getLesson().getProfessor().getId() == p.getId()).collect(Collectors.toList());
            SheduleProfessorLineDTO sheduleLineModel = new SheduleProfessorLineDTO();
            sheduleLineModel.setPairs(collect);
            sheduleLineModel.setProfessor(p);
            line.add(sheduleLineModel);
        }

        departmentShedule.setLines(line);
    }

    public DepartmentSheduleDTO Get() {
        return departmentShedule;
    }


}

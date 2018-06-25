package com.unesco.core.managers.schedule.pairManager;

import com.unesco.core.managers.schedule.pairManager.interfaces.pairList.IPairListManager;
import com.unesco.core.dto.account.ProfessorDTO;
import com.unesco.core.dto.plan.DepartmentDTO;
import com.unesco.core.dto.shedule.DisciplineDTO;
import com.unesco.core.dto.shedule.GroupDTO;
import com.unesco.core.dto.shedule.PairDTO;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class PairListManager implements IPairListManager {

    public List<PairDTO> pairList;

    public PairListManager() {
        pairList = new ArrayList<>();
    }

    //Base

    public void Init(List<PairDTO> PairList) {
        pairList = PairList;
    }

    public List<PairDTO> GetAll() {
        return pairList;
    }

    public List<PairDTO> getPairs(GroupDTO group)
    {
        List<PairDTO> result = new ArrayList<PairDTO>();
        for (PairDTO p : pairList) {
            if(p.getLesson().getGroup().getId() == group.getId())
                result.add(p);
        }
        return result;
    }

    public void ApplayFilter(ProfessorDTO professor)
    {
        List<PairDTO> result = new ArrayList<>();
        for (PairDTO p:pairList) {
            if(p.getLesson().getProfessor().getId() == professor.getId())
                result.add(p);

        }
        pairList = result;
    }

    public void ApplayFilter(GroupDTO group)
    {
        List<PairDTO> result = new ArrayList<>();
        for (PairDTO p:pairList) {
            if(p.getLesson().getGroup().getId() == group.getId())
                result.add(p);

        }
        pairList = result;
    }

    public void ApplayFilter(DisciplineDTO discipline)
    {
        List<PairDTO> result = new ArrayList<>();
        for (PairDTO p:pairList) {
            if(p.getLesson().getDiscipline().getId() == discipline.getId())
                result.add(p);

        }
        pairList = result;
    }

    public void ApplayFilter(DepartmentDTO department)
    {
        List<PairDTO> result = new ArrayList<>();
        for (PairDTO p:pairList) {
            if(p.getLesson().getProfessor().getDepartment().getId() == department.getId())
                result.add(p);

        }
        pairList = result;
    }

}

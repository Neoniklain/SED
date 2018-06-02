package com.unesco.core.managers.schedule.pairManager;

import com.unesco.core.managers.schedule.pairManager.interfaces.pairList.IPairListManager;
import com.unesco.core.models.account.ProfessorModel;
import com.unesco.core.models.plan.DepartmentModel;
import com.unesco.core.models.shedule.DisciplineModel;
import com.unesco.core.models.shedule.GroupModel;
import com.unesco.core.models.shedule.PairModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class PairListManager implements IPairListManager {

    public List<PairModel> pairList;

    public PairListManager() {
        pairList = new ArrayList<>();
    }

    //Base

    public void Init(List<PairModel> PairList) {
        pairList = PairList;
    }

    public List<PairModel> GetAll() {
        return pairList;
    }

    public List<PairModel> getPairs(GroupModel group)
    {
        List<PairModel> result = new ArrayList<PairModel>();
        for (PairModel p : pairList) {
            if(p.getLesson().getGroup().getId() == group.getId())
                result.add(p);
        }
        return result;
    }

    public void ApplayFilter(ProfessorModel professor)
    {
        List<PairModel> result = new ArrayList<>();
        for (PairModel p:pairList) {
            if(p.getLesson().getProfessor().getId() == professor.getId())
                result.add(p);

        }
        pairList = result;
    }

    public void ApplayFilter(GroupModel group)
    {
        List<PairModel> result = new ArrayList<>();
        for (PairModel p:pairList) {
            if(p.getLesson().getGroup().getId() == group.getId())
                result.add(p);

        }
        pairList = result;
    }

    public void ApplayFilter(DisciplineModel discipline)
    {
        List<PairModel> result = new ArrayList<>();
        for (PairModel p:pairList) {
            if(p.getLesson().getDiscipline().getId() == discipline.getId())
                result.add(p);

        }
        pairList = result;
    }

    public void ApplayFilter(DepartmentModel department)
    {
        List<PairModel> result = new ArrayList<>();
        for (PairModel p:pairList) {
            if(p.getLesson().getProfessor().getDepartment().getId() == department.getId())
                result.add(p);

        }
        pairList = result;
    }

}

package com.unesco.core.managers.plan.semesterManager;

import com.unesco.core.managers.plan.semesterManager.interfaces.semester.ISemesterManager;
import com.unesco.core.models.additional.ResponseStatus;
import com.unesco.core.models.plan.SemesterModel;
import com.unesco.core.services.plan.semesterService.ISemesterDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class SemesterManager implements ISemesterManager {

    SemesterModel semester;

    public SemesterManager() {
        semester = new SemesterModel();
    }

    public void Init(SemesterModel Semester) {
        semester = Semester;
    }

    public SemesterModel Get() {
        return semester;
    }

}

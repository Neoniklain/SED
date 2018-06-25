package com.unesco.core.managers.plan.semesterManager;

import com.unesco.core.managers.plan.semesterManager.interfaces.semester.ISemesterManager;
import com.unesco.core.dto.plan.SemesterDTO;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class SemesterManager implements ISemesterManager {

    SemesterDTO semester;

    public SemesterManager() {
        semester = new SemesterDTO();
    }

    public void Init(SemesterDTO Semester) {
        semester = Semester;
    }

    public SemesterDTO Get() {
        return semester;
    }

}

package com.unesco.core.managers.plan.semesterManager;

import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.enums.StatusTypes;
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

    public void init(SemesterDTO Semester) {
        semester = Semester;
    }

    public SemesterDTO get() {
        return semester;
    }

    @Override
    public ResponseStatusDTO validate() {
        ResponseStatusDTO result = new ResponseStatusDTO(StatusTypes.OK);
        return result;
    }
}

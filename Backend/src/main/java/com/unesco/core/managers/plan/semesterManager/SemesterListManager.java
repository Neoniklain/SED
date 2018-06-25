package com.unesco.core.managers.plan.semesterManager;

import com.unesco.core.managers.plan.semesterManager.interfaces.semesterList.ISemesterListManager;
import com.unesco.core.dto.plan.SemesterDTO;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class SemesterListManager implements ISemesterListManager {

    public List<SemesterDTO> semesterList;

    public SemesterListManager() {
        semesterList = new ArrayList<>();
    }

    public void Init(List<SemesterDTO> SemesterList) {
        semesterList = SemesterList;
    }

    public List<SemesterDTO> GetAll() {
        return semesterList;
    }

}

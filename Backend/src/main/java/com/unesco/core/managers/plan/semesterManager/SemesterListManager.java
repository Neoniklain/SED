package com.unesco.core.managers.plan.semesterManager;

import com.unesco.core.managers.plan.semesterManager.interfaces.semesterList.ISemesterListManager;
import com.unesco.core.models.plan.SemesterModel;
import com.unesco.core.services.plan.semesterService.ISemesterDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class SemesterListManager implements ISemesterListManager {

    public List<SemesterModel> semesterList;

    public SemesterListManager() {
        semesterList = new ArrayList<>();
    }

    public void Init(List<SemesterModel> SemesterList) {
        semesterList = SemesterList;
    }

    public List<SemesterModel> GetAll() {
        return semesterList;
    }

}

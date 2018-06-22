package com.unesco.core.managers.schedule.departmentManager;

import com.unesco.core.managers.schedule.departmentManager.interfaces.departmentList.IDepartmentListManager;
import com.unesco.core.models.plan.DepartmentDTO;
import com.unesco.core.services.schedule.departmentService.IDepartmentDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class DepartmentListManager implements IDepartmentListManager {

    @Autowired
    public IDepartmentDataService dataService;

    public List<DepartmentDTO> departmentList;

    public DepartmentListManager() {
        departmentList = new ArrayList<>();
    }

    public void Init(List<DepartmentDTO> DepartmentList) {
        departmentList = DepartmentList;
    }

    public List<DepartmentDTO> GetAll() {
        return departmentList;
    }
}

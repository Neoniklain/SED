package com.unesco.core.managers.schedule.departmentManager;

import com.unesco.core.managers.schedule.departmentManager.interfaces.department.IDepartmentManager;
import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.plan.DepartmentDTO;
import com.unesco.core.services.dataService.schedule.departmentService.IDepartmentDataService;
import com.unesco.core.utils.StatusTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class DepartmentManager implements IDepartmentManager {

    @Autowired
    public IDepartmentDataService dataService;

    DepartmentDTO department;

    public DepartmentManager() {
        department = new DepartmentDTO();
    }

    public void init(DepartmentDTO Department) {
        department = Department;
    }

    public DepartmentDTO get() {
        return department;
    }

    public ResponseStatusDTO validate() {
        ResponseStatusDTO responseStatusDTO = new ResponseStatusDTO();
        responseStatusDTO.setStatus(StatusTypes.OK);
        if (department.getName().equals("")) {
            responseStatusDTO.setStatus(StatusTypes.ERROR);
            responseStatusDTO.addErrors("Не указано название кафедры");
        }
        if (department.getInstitute().getId() == 0) {
            responseStatusDTO.setStatus(StatusTypes.ERROR);
            responseStatusDTO.addErrors("Не указан институт");
        }
        return responseStatusDTO;
    }
}

package com.unesco.core.managers.schedule.departmentManager;

import com.unesco.core.managers.schedule.departmentManager.interfaces.department.IDepartmentManager;
import com.unesco.core.models.additional.ResponseStatus;
import com.unesco.core.models.plan.DepartmentModel;
import com.unesco.core.services.schedule.departmentService.IDepartmentDataService;
import com.unesco.core.utils.StatusTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class DepartmentManager implements IDepartmentManager {

    @Autowired
    public IDepartmentDataService dataService;

    DepartmentModel department;

    public DepartmentManager() {
        department = new DepartmentModel();
    }

    public void Init(DepartmentModel Department) {
        department = Department;
    }

    public DepartmentModel Get() {
        return department;
    }

    public ResponseStatus Validate() {
        ResponseStatus responseStatus = new ResponseStatus();
        responseStatus.setStatus(StatusTypes.OK);
        if (department.getName().equals("")) {
            responseStatus.setStatus(StatusTypes.ERROR);
            responseStatus.addErrors("Не указано название кафедры");
        }
        if (department.getInstitute().getId() == 0) {
            responseStatus.setStatus(StatusTypes.ERROR);
            responseStatus.addErrors("Не указан институт");
        }
        return responseStatus;
    }
}

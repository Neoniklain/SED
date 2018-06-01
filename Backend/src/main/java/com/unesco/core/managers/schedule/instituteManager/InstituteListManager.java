package com.unesco.core.managers.schedule.instituteManager;

import com.unesco.core.managers.schedule.instituteManager.interfaces.instituteList.IInstituteListManager;
import com.unesco.core.models.additional.FilterQuery;
import com.unesco.core.models.shedule.InstituteModel;
import com.unesco.core.services.schedule.instituteService.IInstituteDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class InstituteListManager implements IInstituteListManager {

    public List<InstituteModel> instituteList;

    public InstituteListManager() {
        instituteList = new ArrayList<>();
    }

    //Base

    public void Init(List<InstituteModel> InstituteList) {
        instituteList = InstituteList;
    }

    public List<InstituteModel> GetAll() {
        return instituteList;
    }
}

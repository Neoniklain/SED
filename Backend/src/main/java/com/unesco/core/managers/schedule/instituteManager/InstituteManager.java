package com.unesco.core.managers.schedule.instituteManager;

import com.unesco.core.managers.schedule.instituteManager.interfaces.institute.IInstituteManager;
import com.unesco.core.models.additional.ResponseStatus;
import com.unesco.core.models.shedule.InstituteModel;
import com.unesco.core.services.schedule.instituteService.IInstituteDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class InstituteManager implements IInstituteManager {

    InstituteModel institute;

    public InstituteManager() {
        institute = new InstituteModel();
    }

    public void Init(InstituteModel Institute) {
        institute = Institute;
    }

    public InstituteModel Get() {
        return institute;
    }

}

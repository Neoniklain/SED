package com.unesco.core.managers.schedule.instituteManager;

import com.unesco.core.managers.schedule.instituteManager.interfaces.instituteList.IInstituteListManager;
import com.unesco.core.dto.shedule.InstituteDTO;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class InstituteListManager implements IInstituteListManager {

    public List<InstituteDTO> instituteList;

    public InstituteListManager() {
        instituteList = new ArrayList<>();
    }

    //Base

    public void init(List<InstituteDTO> InstituteList) {
        instituteList = InstituteList;
    }

    public List<InstituteDTO> getAll() {
        return instituteList;
    }
}

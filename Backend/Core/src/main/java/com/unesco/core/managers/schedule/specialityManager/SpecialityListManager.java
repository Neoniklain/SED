package com.unesco.core.managers.schedule.specialityManager;

import com.unesco.core.dto.shedule.InstituteDTO;
import com.unesco.core.dto.shedule.SpecialityDTO;
import com.unesco.core.managers.schedule.specialityManager.interfaces.specialityList.ISpecialityListManager;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class SpecialityListManager implements ISpecialityListManager {

    public List<SpecialityDTO> specialityList;

    public SpecialityListManager() {
        specialityList = new ArrayList<>();
    }

    //Base

    public void init(List<SpecialityDTO> SpecialityList) {
        specialityList = SpecialityList;
    }

    public List<SpecialityDTO> getAll() {
        return specialityList;
    }
}

package com.unesco.core.managers.schedule.specialityManager;

import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.enums.StatusTypes;
import com.unesco.core.dto.shedule.InstituteDTO;
import com.unesco.core.dto.shedule.SpecialityDTO;
import com.unesco.core.managers.schedule.specialityManager.interfaces.speciality.ISpecialityManager;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class SpecialityManager implements ISpecialityManager {

    SpecialityDTO speciality;

    public SpecialityManager() {
        speciality = new SpecialityDTO();
    }

    public void init(SpecialityDTO Speciality) {
        speciality = Speciality;
    }

    public SpecialityDTO get() {
        return speciality;
    }

    public ResponseStatusDTO validate() {
        ResponseStatusDTO responseStatusDTO = new ResponseStatusDTO();
        responseStatusDTO.setStatus(StatusTypes.OK);
        if (speciality.getName().equals("")) {
            responseStatusDTO.setStatus(StatusTypes.ERROR);
            responseStatusDTO.addErrors("Не указано название специальности");
        }
        if (speciality.getDepartment() == null || speciality.getDepartment().getId() == 0) {
            responseStatusDTO.setStatus(StatusTypes.ERROR);
            responseStatusDTO.addErrors("Не указан департамент");
        }
        if (speciality.getInstitute() == null || speciality.getInstitute().getId() == 0) {
            responseStatusDTO.setStatus(StatusTypes.ERROR);
            responseStatusDTO.addErrors("Не указан институт");
        }
        if (speciality.getCode() == null || speciality.getCode() == "") {
            responseStatusDTO.setStatus(StatusTypes.ERROR);
            responseStatusDTO.addErrors("Не указан код");
        }
        return responseStatusDTO;
    }

}

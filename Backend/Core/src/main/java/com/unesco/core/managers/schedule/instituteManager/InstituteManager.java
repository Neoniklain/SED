package com.unesco.core.managers.schedule.instituteManager;

import com.unesco.core.managers.schedule.instituteManager.interfaces.institute.IInstituteManager;
import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.shedule.InstituteDTO;
import com.unesco.core.dto.enums.StatusTypes;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class InstituteManager implements IInstituteManager {

    InstituteDTO institute;

    public InstituteManager() {
        institute = new InstituteDTO();
    }

    public void init(InstituteDTO Institute) {
        institute = Institute;
    }

    public InstituteDTO get() {
        return institute;
    }

    public ResponseStatusDTO validate() {
        ResponseStatusDTO responseStatusDTO = new ResponseStatusDTO();
        responseStatusDTO.setStatus(StatusTypes.OK);
        if (institute.getName().equals("")) {
            responseStatusDTO.setStatus(StatusTypes.ERROR);
            responseStatusDTO.addErrors("Не указано название института");
        }
        return responseStatusDTO;
    }
}

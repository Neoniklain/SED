package com.unesco.core.managers.account.professorManager;

import com.unesco.core.managers.account.professorManager.interfaces.professorList.IProfessorListManager;
import com.unesco.core.dto.account.ProfessorDTO;
import com.unesco.core.services.dataService.account.professorService.IProfessorDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class ProfessorListManager implements IProfessorListManager {

    @Autowired
    public IProfessorDataService dataService;

    public List<ProfessorDTO> professorList;

    public ProfessorListManager() {
        professorList = new ArrayList<>();
    }

    public void init(List<ProfessorDTO> ProfessorList) {
        professorList = ProfessorList;
    }

    public List<ProfessorDTO> getAll() {
        return professorList;
    }
}

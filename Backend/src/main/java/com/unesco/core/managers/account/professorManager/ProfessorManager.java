package com.unesco.core.managers.account.professorManager;

import com.unesco.core.managers.account.professorManager.interfaces.professor.IProfessorManager;
import com.unesco.core.dto.account.ProfessorDTO;
import com.unesco.core.dto.account.UserDTO;
import com.unesco.core.dto.plan.DepartmentDTO;
import com.unesco.core.services.account.professorService.IProfessorDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class ProfessorManager implements IProfessorManager {

    @Autowired
    public IProfessorDataService dataService;

    public ProfessorDTO professor;

    public ProfessorManager() {
        professor = new ProfessorDTO();
    }

    public void Init(ProfessorDTO Professor) {
        professor = Professor;
    }

    public ProfessorDTO Get() {
        return professor;
    }

    public void SetDepartment(DepartmentDTO department)
    {
        professor.setDepartment(department);
    }

    public void Create(UserDTO user) {

        professor.setId(0);
        professor.setUser(user);
        professor.setDepartment(null);
    }

}

package com.unesco.core.managers.account.professorManager;

import com.unesco.core.managers.account.professorManager.interfaces.professor.IProfessorManager;
import com.unesco.core.models.account.ProfessorModel;
import com.unesco.core.models.account.UserModel;
import com.unesco.core.models.plan.DepartmentModel;
import com.unesco.core.services.account.professorService.IProfessorDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class ProfessorManager implements IProfessorManager {

    @Autowired
    public IProfessorDataService dataService;

    public ProfessorModel professor;

    public ProfessorManager() {
        professor = new ProfessorModel();
    }

    public void Init(ProfessorModel Professor) {
        professor = Professor;
    }

    public ProfessorModel Get() {
        return professor;
    }

    public void SetDepartment(DepartmentModel department)
    {
        professor.setDepartment(department);
    }

    public void Create(UserModel user) {

        professor.setId(0);
        professor.setUser(user);
        professor.setDepartment(null);
    }

}

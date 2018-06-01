package com.unesco.core.managers.account.professorManager.interfaces.professor;

import com.unesco.core.managers.IManager;
import com.unesco.core.models.account.ProfessorModel;
import com.unesco.core.models.account.UserModel;
import com.unesco.core.models.plan.DepartmentModel;

public interface IProfessorManager extends IManager<ProfessorModel> {
    void SetDepartment(DepartmentModel department);
    void Create(UserModel user);
}

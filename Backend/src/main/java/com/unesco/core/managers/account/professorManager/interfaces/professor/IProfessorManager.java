package com.unesco.core.managers.account.professorManager.interfaces.professor;

import com.unesco.core.managers.IManager;
import com.unesco.core.models.account.ProfessorDTO;
import com.unesco.core.models.account.UserDTO;
import com.unesco.core.models.plan.DepartmentDTO;

public interface IProfessorManager extends IManager<ProfessorDTO> {
    void SetDepartment(DepartmentDTO department);
    void Create(UserDTO user);
}

package com.unesco.core.managers.account.professorManager.interfaces.professor;

import com.unesco.core.managers.IManager;
import com.unesco.core.dto.account.ProfessorDTO;
import com.unesco.core.dto.account.UserDTO;
import com.unesco.core.dto.plan.DepartmentDTO;

public interface IProfessorManager extends IManager<ProfessorDTO> {
    void SetDepartment(DepartmentDTO department);
    void Create(UserDTO user);
}

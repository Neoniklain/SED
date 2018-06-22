package com.unesco.core.managers.account.studentManager.interfaces.student;

import com.unesco.core.managers.IManager;
import com.unesco.core.models.account.StudentDTO;
import com.unesco.core.models.account.UserDTO;
import com.unesco.core.models.shedule.GroupDTO;

public interface IStudentManager extends IManager<StudentDTO> {

    void SetGroup(GroupDTO group);
    void Create(UserDTO user);
}

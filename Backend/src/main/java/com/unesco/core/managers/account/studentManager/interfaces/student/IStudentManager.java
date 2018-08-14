package com.unesco.core.managers.account.studentManager.interfaces.student;

import com.unesco.core.managers.IManager;
import com.unesco.core.dto.account.StudentDTO;
import com.unesco.core.dto.account.UserDTO;
import com.unesco.core.dto.shedule.GroupDTO;

public interface IStudentManager extends IManager<StudentDTO> {

    void setGroup(GroupDTO group);
    void create(UserDTO user);
}

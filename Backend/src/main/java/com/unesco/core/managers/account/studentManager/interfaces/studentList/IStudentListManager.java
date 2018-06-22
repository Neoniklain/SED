package com.unesco.core.managers.account.studentManager.interfaces.studentList;

import com.unesco.core.managers.IListManager;
import com.unesco.core.models.account.StudentDTO;
import com.unesco.core.models.shedule.GroupDTO;

public interface IStudentListManager extends IListManager<StudentDTO> {
    void ApplayFilter(GroupDTO group);
}

package com.unesco.core.managers.account.studentManager.interfaces.studentList;

import com.unesco.core.managers.IListManager;
import com.unesco.core.models.account.StudentModel;
import com.unesco.core.models.shedule.GroupModel;

public interface IStudentListManager extends IListManager<StudentModel> {
    void ApplayFilter(GroupModel group);
}

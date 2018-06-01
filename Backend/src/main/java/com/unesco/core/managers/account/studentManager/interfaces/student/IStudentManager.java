package com.unesco.core.managers.account.studentManager.interfaces.student;

import com.unesco.core.managers.IManager;
import com.unesco.core.models.account.StudentModel;
import com.unesco.core.models.account.UserModel;
import com.unesco.core.models.shedule.GroupModel;

public interface IStudentManager extends IManager<StudentModel> {

    void SetGroup(GroupModel group);
    void Create(UserModel user);
}

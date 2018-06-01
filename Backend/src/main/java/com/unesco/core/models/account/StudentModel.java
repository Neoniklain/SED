package com.unesco.core.models.account;

import com.unesco.core.models.account.UserModel;
import com.unesco.core.models.shedule.GroupModel;

public class StudentModel extends UserModel {

    public GroupModel group;

    public StudentModel() {
    }

    public GroupModel getGroup() {
        return group;
    }
    public void setGroup(GroupModel group) {
        this.group = group;
    }
}

package com.unesco.core.models;

import com.unesco.core.models.account.UserModel;

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

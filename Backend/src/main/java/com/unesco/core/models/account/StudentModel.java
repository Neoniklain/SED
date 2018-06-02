package com.unesco.core.models.account;

import com.unesco.core.models.shedule.GroupModel;

public class StudentModel {

    private long id;
    public GroupModel group;
    public UserModel user;

    public StudentModel() {}

    public GroupModel getGroup() {
        return group;
    }
    public void setGroup(GroupModel group) {
        this.group = group;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public UserModel getUser() {
        return user;
    }
    public void setUser(UserModel user) {
        this.user = user;
    }

}

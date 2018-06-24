package com.unesco.core.models.account;

import com.unesco.core.models.shedule.GroupDTO;

public class StudentDTO {

    private long id;
    public GroupDTO group;
    public UserDTO user;

    public StudentDTO() {}

    public GroupDTO getGroup() {
        return group;
    }
    public void setGroup(GroupDTO group) {
        this.group = group;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public UserDTO getUser() {
        return user;
    }
    public void setUser(UserDTO user) {
        this.user = user;
    }

}
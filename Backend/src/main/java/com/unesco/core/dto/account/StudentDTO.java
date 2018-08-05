package com.unesco.core.dto.account;

import com.unesco.core.dto.shedule.GroupDTO;

public class StudentDTO {

    private long id;
    private GroupDTO group;
    private UserDTO user;

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

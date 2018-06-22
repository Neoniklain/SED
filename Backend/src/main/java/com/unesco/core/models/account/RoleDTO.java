package com.unesco.core.models.account;

public class RoleDTO {

    public long id;
    public String roleName;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public RoleDTO() {
        this.roleName = "";
    }

}

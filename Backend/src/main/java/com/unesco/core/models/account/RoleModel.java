package com.unesco.core.models.account;

public class RoleModel {

    public String roleName;

    public String getRoleName() {
        return roleName;
    }
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public RoleModel(String roleName) {
        this.roleName = roleName;
    }
}

package com.unesco.core.models.account;

import com.unesco.core.entities.account.Role;

import javax.swing.*;

public class RoleModel {

    public String roleName;
    public String roleNameRus;

    public String getRoleName() {
        return roleName;
    }
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleNameRus() {
        return roleNameRus;
    }
    public void setRoleNameRus(String roleNameRus) {
        this.roleNameRus = roleNameRus;
    }

    /*public RoleModel(String roleName) {
        this.roleName = roleName;
    }*/

    public RoleModel(Role role)
    {
        this.roleNameRus = role.getRoleNameRus();
        this.roleName = role.getRoleName();
    }
}

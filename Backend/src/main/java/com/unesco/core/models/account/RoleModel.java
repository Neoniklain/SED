package com.unesco.core.models.account;

import com.unesco.core.entities.account.Role;
import com.unesco.core.models.additional.EntityModel;

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

    public RoleModel() {
        this.roleName = "";
    }

}

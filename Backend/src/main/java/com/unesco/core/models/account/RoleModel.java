package com.unesco.core.models.account;

import com.unesco.core.entities.account.Role;
import com.unesco.core.models.additional.EntityModel;

public class RoleModel {

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

    public RoleModel() {
        this.roleName = "";
    }

}

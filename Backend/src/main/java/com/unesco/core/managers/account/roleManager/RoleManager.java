package com.unesco.core.managers.account.roleManager;

import com.unesco.core.managers.account.roleManager.interfaces.role.IRoleManager;
import com.unesco.core.models.account.RoleModel;
import com.unesco.core.models.additional.ResponseStatus;
import com.unesco.core.services.account.roleService.IRoleDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class RoleManager implements IRoleManager {

    public RoleModel role;

    public RoleManager() {
        role = new RoleModel();
    }

    public void Init(RoleModel Professor) {
        role = Professor;
    }

    public RoleModel Get() {
        return role;
    }

}

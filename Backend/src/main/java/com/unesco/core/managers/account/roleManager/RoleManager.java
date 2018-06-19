package com.unesco.core.managers.account.roleManager;

import com.unesco.core.managers.account.roleManager.interfaces.role.IRoleManager;
import com.unesco.core.models.account.RoleModel;
import com.unesco.core.models.additional.ResponseStatus;
import com.unesco.core.utils.StatusTypes;
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

    public ResponseStatus Validate() {
        ResponseStatus responseStatus = new ResponseStatus();
        responseStatus.setStatus(StatusTypes.OK);
        if (role.getRoleName().equals("")) {
            responseStatus.setStatus(StatusTypes.ERROR);
            responseStatus.addErrors("Не задана роль");
        }
        return responseStatus;
    }

}

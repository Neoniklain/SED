package com.unesco.core.managers.account.roleManager;

import com.unesco.core.managers.account.roleManager.interfaces.role.IRoleManager;
import com.unesco.core.models.account.RoleDTO;
import com.unesco.core.models.additional.ResponseStatusDTO;
import com.unesco.core.utils.StatusTypes;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class RoleManager implements IRoleManager {

    public RoleDTO role;

    public RoleManager() {
        role = new RoleDTO();
    }

    public void Init(RoleDTO Professor) {
        role = Professor;
    }

    public RoleDTO Get() {
        return role;
    }

    public ResponseStatusDTO Validate() {
        ResponseStatusDTO responseStatusDTO = new ResponseStatusDTO();
        responseStatusDTO.setStatus(StatusTypes.OK);
        if (role.getRoleName().equals("")) {
            responseStatusDTO.setStatus(StatusTypes.ERROR);
            responseStatusDTO.addErrors("Не задана роль");
        }
        return responseStatusDTO;
    }

}

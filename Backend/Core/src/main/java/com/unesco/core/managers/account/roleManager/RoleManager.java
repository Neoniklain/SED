package com.unesco.core.managers.account.roleManager;

import com.unesco.core.managers.account.roleManager.interfaces.role.IRoleManager;
import com.unesco.core.dto.account.RoleDTO;
import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.enums.StatusTypes;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class RoleManager implements IRoleManager {

    public RoleDTO role;

    public RoleManager() {
        role = new RoleDTO();
    }

    public void init(RoleDTO Professor) {
        role = Professor;
    }

    public RoleDTO get() {
        return role;
    }

    public ResponseStatusDTO validate() {
        ResponseStatusDTO responseStatusDTO = new ResponseStatusDTO();
        responseStatusDTO.setStatus(StatusTypes.OK);
        if (role.getRoleName().equals("")) {
            responseStatusDTO.setStatus(StatusTypes.ERROR);
            responseStatusDTO.addErrors("Не задана роль");
        }
        return responseStatusDTO;
    }

}

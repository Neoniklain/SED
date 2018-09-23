package com.unesco.core.managers.account.roleManager;

import com.unesco.core.managers.account.roleManager.interfaces.roleList.IRoleListManager;
import com.unesco.core.dto.account.RoleDTO;
import com.unesco.core.dto.enums.RoleType;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class RoleListManager implements IRoleListManager {

    public List<RoleDTO> roleList;

    public RoleListManager() {
        roleList = new ArrayList<>();
    }

    public void init(List<RoleDTO> RoleList) {
        roleList = RoleList;
    }

    public List<RoleDTO> getAll() {
        return roleList;
    }

    public RoleDTO GetRole(RoleType roleType) {
        for(RoleDTO r :roleList) {
            if (r.getRoleName().equals(roleType.toString())) {
                return r;
            }
        }
        return null;
    }

    public Boolean ContainRole(RoleType roleType) {
        for(RoleDTO r :roleList) {
            if (r.getRoleName().equals(roleType.toString())) {
                return true;
            }
        }
        return false;
    }
}

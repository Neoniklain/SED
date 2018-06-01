package com.unesco.core.managers.account.roleManager;

import com.unesco.core.managers.account.roleManager.interfaces.roleList.IRoleListManager;
import com.unesco.core.models.account.RoleModel;
import com.unesco.core.models.enums.RoleType;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class RoleListManager implements IRoleListManager {

    public List<RoleModel> roleList;

    public RoleListManager() {
        roleList = new ArrayList<>();
    }

    public void Init(List<RoleModel> RoleList) {
        roleList = RoleList;
    }

    public List<RoleModel> GetAll() {
        return roleList;
    }

    public RoleModel GetRole(RoleType roleType) {
        for(RoleModel r :roleList) {
            if (r.getRoleName().equals(roleType.toString())) {
                return r;
            }
        }
        return null;
    }

    public Boolean ContainRole(RoleType roleType) {
        for(RoleModel r :roleList) {
            if (r.getRoleName().equals(roleType.toString())) {
                return true;
            }
        }
        return false;
    }
}

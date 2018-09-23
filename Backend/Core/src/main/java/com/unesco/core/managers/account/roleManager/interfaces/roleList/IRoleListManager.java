package com.unesco.core.managers.account.roleManager.interfaces.roleList;

import com.unesco.core.managers.IListManager;
import com.unesco.core.dto.account.RoleDTO;
import com.unesco.core.dto.enums.RoleType;

public interface IRoleListManager extends IListManager<RoleDTO> {

    RoleDTO GetRole(RoleType roleType);
    Boolean ContainRole(RoleType roleType);
}

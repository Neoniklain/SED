package com.unesco.core.managers.account.roleManager.interfaces.roleList;

import com.unesco.core.managers.IListManager;
import com.unesco.core.models.account.RoleModel;
import com.unesco.core.models.enums.RoleType;

public interface IRoleListManager extends IListManager<RoleModel> {

    RoleModel GetRole(RoleType roleType);
    Boolean ContainRole(RoleType roleType);
}

package com.unesco.core.services.account.roleService;

import com.unesco.core.models.account.RoleModel;
import com.unesco.core.models.additional.FilterQuery;
import com.unesco.core.services.IDataService;

import java.util.List;

public interface IRoleDataService extends IDataService<RoleModel> {
    List<RoleModel> GetPage(FilterQuery filter);
    RoleModel GetByName(String name);
}

package com.unesco.core.services.account.roleService;

import com.unesco.core.models.account.RoleDTO;
import com.unesco.core.models.additional.FilterQueryDTO;
import com.unesco.core.services.IDataService;

import java.util.List;

public interface IRoleDataService extends IDataService<RoleDTO> {
    List<RoleDTO> GetPage(FilterQueryDTO filter);
    RoleDTO GetByName(String name);
}

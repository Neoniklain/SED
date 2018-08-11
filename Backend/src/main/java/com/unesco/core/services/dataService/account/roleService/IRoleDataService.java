package com.unesco.core.services.dataService.account.roleService;

import com.unesco.core.dto.account.RoleDTO;
import com.unesco.core.dto.additional.FilterQueryDTO;
import com.unesco.core.services.dataService.IDataService;

import java.util.List;

public interface IRoleDataService extends IDataService<RoleDTO> {
    List<RoleDTO> getPage(FilterQueryDTO filter);
    RoleDTO getByName(String name);
}

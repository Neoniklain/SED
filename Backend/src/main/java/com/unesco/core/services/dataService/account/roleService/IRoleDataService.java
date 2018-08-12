package com.unesco.core.services.dataService.account.roleService;

import com.unesco.core.dto.account.RoleDTO;
import com.unesco.core.dto.additional.FilterQueryDTO;
import com.unesco.core.dto.additional.PageResultDTO;
import com.unesco.core.services.dataService.IDataService;

public interface IRoleDataService extends IDataService<RoleDTO> {
    PageResultDTO<RoleDTO> getPage(FilterQueryDTO filter);
    RoleDTO getByName(String name);
}

package com.unesco.core.services.dataService.account.userService;

import com.unesco.core.dto.account.UserDTO;
import com.unesco.core.dto.additional.FilterQueryDTO;
import com.unesco.core.dto.additional.PageResultDTO;
import com.unesco.core.services.dataService.IDataService;

import java.util.List;

public interface IUserDataService extends IDataService<UserDTO> {
    PageResultDTO<UserDTO> getPage(FilterQueryDTO filter);
    UserDTO getByUsername(String username);
    List<UserDTO> getByRoleName(String rolename);
}

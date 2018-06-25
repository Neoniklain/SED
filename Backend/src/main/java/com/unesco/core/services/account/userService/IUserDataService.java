package com.unesco.core.services.account.userService;

import com.unesco.core.dto.account.UserDTO;
import com.unesco.core.dto.additional.FilterQueryDTO;
import com.unesco.core.services.IDataService;

import java.util.List;

public interface IUserDataService extends IDataService<UserDTO> {
    List<UserDTO> GetPage(FilterQueryDTO filter);
    UserDTO GetByUsername(String username);
}

package com.unesco.core.services.userService;

import com.unesco.core.dto.account.UserDTO;
import org.springframework.stereotype.Service;

@Service
public interface IUserService {

    UserDTO getCurrentUser();

}

package com.unesco.core.services.userService;

import com.unesco.core.dto.account.UserDTO;
import com.unesco.core.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private CustomUserDetailsService _CustomUserDetailsService;

    public UserDTO getCurrentUser() {
        return new UserDTO(_CustomUserDetailsService.getUserDetails());
    }

}

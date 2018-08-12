package com.unesco.core.services.accessControlService;

import com.unesco.core.dto.account.UserDTO;
import com.unesco.core.dto.enums.AccessRightType;
import com.unesco.core.dto.enums.RoleType;
import org.springframework.stereotype.Service;

@Service
public interface IAccessСontrolService {

    boolean checkUserAccess(UserDTO user, AccessRightType right);
    boolean checkUserAccess(UserDTO user, RoleType role);

}

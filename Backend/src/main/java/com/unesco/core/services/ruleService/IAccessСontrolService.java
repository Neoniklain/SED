package com.unesco.core.services.ruleService;

import com.unesco.core.dto.account.UserDTO;
import com.unesco.core.dto.enums.AccessRightType;
import org.springframework.stereotype.Service;

@Service
public interface IAccessСontrolService {

    boolean checkUserAccess(UserDTO user, AccessRightType right);

}

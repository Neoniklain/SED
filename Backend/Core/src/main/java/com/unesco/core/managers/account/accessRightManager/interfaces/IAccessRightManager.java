package com.unesco.core.managers.account.accessRightManager.interfaces;

import com.unesco.core.dto.AccessRightDTO;
import com.unesco.core.dto.UserAccessRightDTO;
import com.unesco.core.managers.IValidateManager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IAccessRightManager extends IValidateManager {

    void init(UserAccessRightDTO userAccessRightDTO, List<AccessRightDTO> rights);
    UserAccessRightDTO get();

}

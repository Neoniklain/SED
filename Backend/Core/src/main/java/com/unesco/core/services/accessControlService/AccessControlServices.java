package com.unesco.core.services.accessControlService;

import com.unesco.core.dto.account.UserAccessRightDTO;
import com.unesco.core.dto.account.UserAccessRowDTO;
import com.unesco.core.dto.account.UserDTO;
import com.unesco.core.dto.enums.AccessRightType;
import com.unesco.core.dto.enums.RoleType;
import com.unesco.core.managers.account.roleManager.interfaces.roleList.IRoleListManager;
import com.unesco.core.services.dataService.account.userAccessRight.IUserAccessRightDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccessControlServices implements IAccessControlService {

    @Autowired
    private IUserAccessRightDataService userAccessRightDataService;
    @Autowired
    private IRoleListManager roleListManager;

    public boolean checkUserAccess(UserDTO user, AccessRightType right) {

        UserAccessRightDTO userAccessRight = userAccessRightDataService.get(user.getId());
        roleListManager.init(user.getRoles());
        if(roleListManager.ContainRole(RoleType.ADMIN)) return true;

        UserAccessRowDTO userAccessRowDTO = null;
        for (UserAccessRowDTO row:userAccessRight.getRights()) {
            if(row.getRight().getName() == right.toString()) {
                userAccessRowDTO = row;
                break;
            }
        }

        if(userAccessRowDTO == null) return false;
        return userAccessRowDTO.isAllow();
    }

    public boolean checkUserAccess(UserDTO user, RoleType role) {

        UserAccessRightDTO userAccessRight = userAccessRightDataService.get(user.getId());
        roleListManager.init(user.getRoles());

        if(roleListManager.ContainRole(RoleType.ADMIN)) return true;
        if(roleListManager.ContainRole(role)) return true;

        return false;
    }

}

package com.unesco.core.managers.account.accessRightManager;

import com.unesco.core.dto.account.AccessRightDTO;
import com.unesco.core.dto.account.UserAccessRightDTO;
import com.unesco.core.dto.account.UserAccessRowDTO;
import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.enums.StatusTypes;
import com.unesco.core.managers.account.accessRightManager.interfaces.IAccessRightManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccessRightManager implements IAccessRightManager {

    private UserAccessRightDTO userAccessRightDTO;
    private List<AccessRightDTO> rights;

    public void init(UserAccessRightDTO userAccessRightDTO, List<AccessRightDTO> rights) {
        this.userAccessRightDTO = userAccessRightDTO;
        this.rights = rights;

        List<UserAccessRowDTO> createRights = new ArrayList<UserAccessRowDTO>();

        for (AccessRightDTO right: rights) {

            List<UserAccessRowDTO> find = this.userAccessRightDTO.getRights()
                    .stream().filter(x -> x.getRight().getName() == right.getName())
                    .collect(Collectors.toList());

            if(find.size() == 0) {
                UserAccessRowDTO newRightRow= new UserAccessRowDTO();
                newRightRow.setRight(right);
                newRightRow.setAllow(false);
                createRights.add(newRightRow);
            }
        }
        createRights.addAll(this.userAccessRightDTO.getRights());
        this.userAccessRightDTO.setRights(createRights);
    }

    public ResponseStatusDTO validate() {
        ResponseStatusDTO result = new ResponseStatusDTO(StatusTypes.OK);
        return result;
    }

    public UserAccessRightDTO get() {
        return userAccessRightDTO;
    }



}

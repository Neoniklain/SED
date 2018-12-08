package com.unesco.core.services.dataService.account.userAccessRight;

import com.unesco.core.dto.account.AccessRightDTO;
import com.unesco.core.dto.account.UserAccessRightDTO;
import com.unesco.core.dto.account.UserAccessRowDTO;
import com.unesco.core.dto.account.UserDTO;
import com.unesco.core.dto.enums.AccessRightType;
import com.unesco.core.entities.account.AccessRightEntity;
import com.unesco.core.entities.account.UserEntity;
import com.unesco.core.entities.account.UserToAccessRightEntity;
import com.unesco.core.repositories.account.AccessRightRepository;
import com.unesco.core.repositories.account.UserRepository;
import com.unesco.core.repositories.account.UserToAccessRightRepository;
import com.unesco.core.services.mapperService.IMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserAccessRightDataService implements IUserAccessRightDataService {

    @Autowired
    private IMapperService mapperService;
    @Autowired
    private UserToAccessRightRepository userAccessRightRepository;
    @Autowired
    private AccessRightRepository accessRightRepository;
    @Autowired
    private UserRepository userRepository;

    public List<UserAccessRightDTO> getAll()
    {
        List<UserAccessRightDTO> model = new ArrayList<>();
        Iterable<UserEntity> allUsers = userRepository.findAll();
        for (UserEntity user: allUsers) {
            model.add(get(user.getId()));
        }
        return model;
    }

    public UserAccessRightDTO get(long id)
    {
        UserAccessRightDTO model = new UserAccessRightDTO();
        UserEntity user = userRepository.findById(id);
        model.setUser((UserDTO) mapperService.toDto(user));

        List<UserToAccessRightEntity> allByUser = userAccessRightRepository.findAllByUserId(id);

        List<UserAccessRowDTO> rights =  new ArrayList<>();
        for (UserToAccessRightEntity userAccess: allByUser) {
            UserAccessRowDTO userAccessRowDTO = new UserAccessRowDTO();
            userAccessRowDTO.setRight((AccessRightDTO)mapperService.toDto(userAccess.getRight()));
            userAccessRowDTO.setAllow(userAccess.isAllow());
            rights.add(userAccessRowDTO);
        }
        model.setRights(rights);
        return model;
    }

    public void delete(long userId, AccessRightType rightType)
    {
        UserEntity user = userRepository.findById(userId);
        AccessRightEntity accessRight = accessRightRepository.findByName(rightType.toString());
        if(user == null || accessRight == null)
            return;

        UserToAccessRightEntity userAcces = userAccessRightRepository.findByUserIdAndRightId(user.getId(), accessRight.getId());
        if(userAcces == null)
            return;

        userAccessRightRepository.delete(userAcces.getId());
    }

    public UserAccessRightDTO save(UserAccessRightDTO userAccessRight)
    {
        for (UserAccessRowDTO row: userAccessRight.getRights()) {
            save(userAccessRight.getUser().getId(), AccessRightType.valueOf(row.getRight().getName()), row.isAllow());
        }
        return userAccessRight;
    }

    public boolean save(long userId, AccessRightType rightName, boolean allow)
    {
        UserEntity user = userRepository.findById(userId);
        AccessRightEntity accessRight = accessRightRepository.findByName(rightName.toString());
        if(user == null || accessRight == null)
            return false;

        UserToAccessRightEntity userAcces = userAccessRightRepository.findByUserIdAndRightId(user.getId(), accessRight.getId());
        if(userAcces == null && !allow) return true;

        if(userAcces == null) {
            userAcces = new UserToAccessRightEntity();
            userAcces.setUser(user);
            userAcces.setRight(accessRight);
            userAcces.setAllow(allow);
        } else {
            userAcces.setAllow(allow);
        }

        try {
            userAccessRightRepository.save(userAcces);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
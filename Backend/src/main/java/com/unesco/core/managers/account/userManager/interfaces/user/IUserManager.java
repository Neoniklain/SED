package com.unesco.core.managers.account.userManager.interfaces.user;

import com.unesco.core.managers.IManager;
import com.unesco.core.managers.IValidateManager;
import com.unesco.core.models.account.RoleModel;
import com.unesco.core.models.account.UserModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUserManager extends IManager<UserModel>, IValidateManager {

    void Init(UserModel User);
    void Create(UserModel User, List<RoleModel> roleList);
    void CleanPassField();

    UserModel Get();
}

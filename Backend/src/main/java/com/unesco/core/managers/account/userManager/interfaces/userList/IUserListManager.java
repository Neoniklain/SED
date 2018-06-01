package com.unesco.core.managers.account.userManager.interfaces.userList;

import com.unesco.core.managers.IListManager;
import com.unesco.core.models.account.UserModel;

import java.util.List;

public interface IUserListManager extends IListManager<UserModel> {

    void Init(List<UserModel> User);
    void CleanPassField();
    List<UserModel> GetByFio(String fio);
    UserModel GetByUsername(String username);

}

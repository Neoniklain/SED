package com.unesco.core.managers.account.userManager.interfaces.userList;

import com.unesco.core.managers.IListManager;
import com.unesco.core.models.account.UserDTO;

import java.util.List;

public interface IUserListManager extends IListManager<UserDTO> {

    void Init(List<UserDTO> User);
    void CleanPassField();
    List<UserDTO> GetByFio(String fio);
    UserDTO GetByUsername(String username);

}

package com.unesco.core.managers.account.userManager;

import com.unesco.core.managers.account.userManager.interfaces.userList.IUserListManager;
import com.unesco.core.dto.account.UserDTO;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class UserListManager implements IUserListManager {

    public List<UserDTO> userList;

    public UserListManager() {
        userList = new ArrayList<>();
    }

    public void Init(List<UserDTO> UserList) {
        userList = UserList;
    }

    public void CleanPassField() {
        for (UserDTO u: userList) {
            u.setPassword("");
        }
    }

    public List<UserDTO> GetAll() {
        return userList;
    }

    public List<UserDTO> GetByFio(String fio)
    {
        List<UserDTO> result = new ArrayList<>();
        for (UserDTO item : userList) {
            if(item.getUserFIO().toLowerCase().contains(fio.toLowerCase()))
            {
                result.add(item);
            }
        }
        return result;
    }

    public UserDTO GetByUsername(String username)
    {
        for (UserDTO item : userList) {
            if(item.getUsername().toLowerCase().contains(username.toLowerCase()))
            {
                return item;
            }
        }
        return null;
    }
}

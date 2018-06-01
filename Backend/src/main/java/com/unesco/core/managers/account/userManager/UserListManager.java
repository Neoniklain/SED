package com.unesco.core.managers.account.userManager;

import com.unesco.core.managers.account.userManager.interfaces.userList.IUserListManager;
import com.unesco.core.models.account.UserModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class UserListManager implements IUserListManager {

    public List<UserModel> userList;

    public UserListManager() {
        userList = new ArrayList<>();
    }

    public void Init(List<UserModel> UserList) {
        userList = UserList;
    }

    public void CleanPassField() {
        for (UserModel u: userList) {
            u.setPassword("");
        }
    }

    public List<UserModel> GetAll() {
        return userList;
    }

    public List<UserModel> GetByFio(String fio)
    {
        List<UserModel> result = new ArrayList<>();
        for (UserModel item : userList) {
            if(item.getUserFIO().toLowerCase().contains(fio.toLowerCase()))
            {
                result.add(item);
            }
        }
        return result;
    }

    public UserModel GetByUsername(String username)
    {
        for (UserModel item : userList) {
            if(item.getUsername().toLowerCase().contains(username.toLowerCase()))
            {
                return item;
            }
        }
        return null;
    }
}

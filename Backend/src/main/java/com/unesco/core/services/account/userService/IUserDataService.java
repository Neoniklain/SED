package com.unesco.core.services.account.userService;

import com.unesco.core.models.account.UserModel;
import com.unesco.core.models.additional.FilterQuery;
import com.unesco.core.services.IDataService;

import java.util.List;

public interface IUserDataService extends IDataService<UserModel> {
    List<UserModel> GetPage(FilterQuery filter);
    UserModel GetByUsername(String username);
}

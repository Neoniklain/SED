package com.unesco.core.services.account.userService;

import com.unesco.core.entities.account.User;
import com.unesco.core.models.account.RoleModel;
import com.unesco.core.models.account.UserModel;
import com.unesco.core.models.additional.FilterQuery;
import com.unesco.core.repositories.account.UserRepository;
import com.unesco.core.services.account.roleService.RoleDataService;
import com.unesco.core.services.mapperService.IMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDataService implements IUserDataService {
    @Autowired
    private IMapperService mapperService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleDataService roleDataService;

    public List<UserModel> GetPage(FilterQuery filter) {
        int rows = filter.getRows()>0? filter.getRows() : (int) userRepository.count();
        int start = rows>0 ? filter.getFirst()/rows: 1;
        List<User> entitys = userRepository.findWithFilter(new PageRequest(start, rows == 0 ? 10 : rows), filter.getGlobalFilter());
        List<UserModel> result = new ArrayList<UserModel>();
        for (User e: entitys) {
            result.add((UserModel) mapperService.toModel(e));
        }
        return result;
    }

    public List<UserModel> GetAll()
    {
        List<UserModel> modelList = new ArrayList<>();
        Iterable<User> entityList = userRepository.findAll();
        for (User item: entityList) {
            UserModel model = (UserModel) mapperService.toModel(item);
            modelList.add(model);
        }
        return modelList;
    }

    public UserModel Get(long id)
    {
        User entity = userRepository.findOne(id);
        UserModel model = (UserModel) mapperService.toModel(entity);
        return model;
    }

    public UserModel GetByUsername(String username)
    {
        User entity = userRepository.findByUsername(username);
        UserModel model = (UserModel) mapperService.toModel(entity);
        return model;
    }

    public void Delete(long id)
    {
        userRepository.delete(id);
    }

    public UserModel Save(UserModel user)
    {
        List<RoleModel> roles = user.getRoles();
        for (RoleModel role: roles) {
            RoleModel findRole = roleDataService.GetByName(role.roleName);
            if(findRole != null) {
                roles.remove(role);
                roles.add(findRole);
            }
        }
        user.setRoles(roles);

        User entity = (User) mapperService.toEntity(user);
        entity = userRepository.save(entity);

        UserModel model = (UserModel) mapperService.toModel(entity);
        return model;
    }

}

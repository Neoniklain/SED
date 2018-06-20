package com.unesco.core.managers.account.userManager;

import com.unesco.core.managers.account.professorManager.interfaces.professor.IProfessorManager;
import com.unesco.core.managers.account.roleManager.interfaces.roleList.IRoleListManager;
import com.unesco.core.managers.account.studentManager.interfaces.student.IStudentManager;
import com.unesco.core.managers.account.userManager.interfaces.user.IUserManager;
import com.unesco.core.models.account.RoleModel;
import com.unesco.core.models.account.UserModel;
import com.unesco.core.models.additional.ResponseStatus;
import com.unesco.core.models.enums.RoleType;
import com.unesco.core.utils.StatusTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class UserManager implements IUserManager {

    @Autowired
    public IProfessorManager professorManager;
    @Autowired
    public IStudentManager studentManager;
    @Autowired
    public IRoleListManager roleListManager;

    public UserModel user;

    UserManager() {
        user = new UserModel();
    }

    public void Init(UserModel User) {
        user = User;
    }

    public UserModel Get() {
        return user;
    }

    public UserModel GetWithoutPasss() {
        return user;
    }

    public void CleanPassField() {
        user.setPassword("");
    }

    public void Create(UserModel User, List<RoleModel> roleList)
    {
        roleListManager.Init(roleList);
        user.setEmail(User.getEmail());
        user.setUserFIO(User.getUserFIO());
        user.setUsername(User.getUsername());
        user.setPassword(User.getPassword());

        // Если роли нет назначаем роль пользователя
        if(User.getRoles().size() == 0) {
            List<RoleModel> roles = new ArrayList<RoleModel>();
            RoleModel r = roleListManager.GetRole(RoleType.GUEST);
            roles.add(r);
            user.setRoles(roles);
        }
        else {
            user.setRoles(User.getRoles());
        }

    }

    public ResponseStatus Validate() {
        ResponseStatus responseStatus = new ResponseStatus();
        responseStatus.setStatus(StatusTypes.OK);
        if (user.getRoles().size() == 0) {
            responseStatus.addErrors("Не указана роль. Назначена роль 'ГОСТЬ'.");
        }
        if (user.getUserFIO().equals("")) {
            responseStatus.setStatus(StatusTypes.ERROR);
            responseStatus.addErrors("Не указано ФИО.");
        }
        if (user.getUsername().equals("")) {
            responseStatus.setStatus(StatusTypes.ERROR);
            responseStatus.addErrors("Не указан логин.");
        }
        if (user.getPassword().equals("")) {
            responseStatus.setStatus(StatusTypes.ERROR);
            responseStatus.addErrors("Не указан пароль.");
        }
        if (user.getPassword().length() < 5) {
            responseStatus.setStatus(StatusTypes.ERROR);
            responseStatus.addErrors("Пароль должен быть больше 5 символов.");
        }
        if (user.getEmail().equals("")) {
            responseStatus.setStatus(StatusTypes.ERROR);
            responseStatus.addErrors("Не указан email.");
        }
        return responseStatus;
    }


}

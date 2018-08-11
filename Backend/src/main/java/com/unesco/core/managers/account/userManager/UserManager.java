package com.unesco.core.managers.account.userManager;

import com.unesco.core.managers.account.professorManager.interfaces.professor.IProfessorManager;
import com.unesco.core.managers.account.roleManager.interfaces.roleList.IRoleListManager;
import com.unesco.core.managers.account.studentManager.interfaces.student.IStudentManager;
import com.unesco.core.managers.account.userManager.interfaces.user.IUserManager;
import com.unesco.core.dto.account.RoleDTO;
import com.unesco.core.dto.account.UserDTO;
import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.enums.RoleType;
import com.unesco.core.utils.StatusTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class UserManager implements IUserManager {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    public IProfessorManager professorManager;
    @Autowired
    public IStudentManager studentManager;
    @Autowired
    public IRoleListManager roleListManager;

    public UserDTO user;

    UserManager() {
        user = new UserDTO();
    }

    public void init(UserDTO User) {
        user = User;
    }

    public UserDTO get() {
        return user;
    }

    public UserDTO GetWithoutPasss() {
        return user;
    }

    public void CleanPassField() {
        user.setPassword("");
    }

    public void Create(UserDTO User, List<RoleDTO> roleList)
    {
        roleListManager.init(roleList);
        user.setEmail(User.getEmail());
        user.setUserFIO(User.getUserFIO());
        user.setUsername(User.getUsername());
        user.setPhoto(User.getPhoto());
        user.setPassword(passwordEncoder.encode(User.getPassword()));

        // Если роли нет назначаем роль пользователя
        if(User.getRoles().size() == 0) {
            List<RoleDTO> roles = new ArrayList<RoleDTO>();
            RoleDTO r = roleListManager.GetRole(RoleType.GUEST);
            roles.add(r);
            user.setRoles(roles);
        }
        else {
            user.setRoles(User.getRoles());
        }

    }

    public ResponseStatusDTO ChangePassword(String newPass, String oldPass) {
        ResponseStatusDTO responseStatusDTO = new ResponseStatusDTO();
        responseStatusDTO.setStatus(StatusTypes.OK);
        if (newPass.equals("")) {
            responseStatusDTO.setStatus(StatusTypes.ERROR);
            responseStatusDTO.addErrors("Не указан новый пароль.");
            return responseStatusDTO;
        }
        if (newPass.equals("")) {
            responseStatusDTO.setStatus(StatusTypes.ERROR);
            responseStatusDTO.addErrors("Не указан старый пароль.");
            return responseStatusDTO;
        }
        if (newPass.length() < 5) {
            responseStatusDTO.setStatus(StatusTypes.ERROR);
            responseStatusDTO.addErrors("Новый пароль меньше 5 символов.");
            return responseStatusDTO;
        }
        if (!passwordEncoder.matches(oldPass,user.getPassword())) {
            responseStatusDTO.setStatus(StatusTypes.ERROR);
            responseStatusDTO.addErrors("Указан не верный пароль.");
            return responseStatusDTO;
        }
        if (newPass.equals(oldPass) || user.getPassword().equals(passwordEncoder.encode(newPass))) {
            responseStatusDTO.setStatus(StatusTypes.ERROR);
            responseStatusDTO.addErrors("Старый и новый пароль - одинаковые.");
            return responseStatusDTO;
        }

        user.setPassword(passwordEncoder.encode(newPass));

        return responseStatusDTO;
    }

    public ResponseStatusDTO ChangePhoto(String photo) {
        ResponseStatusDTO responseStatusDTO = new ResponseStatusDTO();
        responseStatusDTO.setStatus(StatusTypes.OK);
        if (photo.equals("")) {
            responseStatusDTO.setStatus(StatusTypes.ERROR);
            responseStatusDTO.addErrors("Не задана новыая фотография.");
            return responseStatusDTO;
        }

        user.setPhoto(photo);

        return responseStatusDTO;
    }

    public ResponseStatusDTO validate() {
        ResponseStatusDTO responseStatusDTO = new ResponseStatusDTO();
        responseStatusDTO.setStatus(StatusTypes.OK);
        if (user.getRoles().size() == 0) {
            responseStatusDTO.addErrors("Не указана роль. Назначена роль 'ГОСТЬ'.");
        }
        if (user.getUserFIO().equals("")) {
            responseStatusDTO.setStatus(StatusTypes.ERROR);
            responseStatusDTO.addErrors("Не указано ФИО.");
        }
        if (user.getUsername().equals("")) {
            responseStatusDTO.setStatus(StatusTypes.ERROR);
            responseStatusDTO.addErrors("Не указан логин.");
        }
        if (user.getPassword().equals("")) {
            responseStatusDTO.setStatus(StatusTypes.ERROR);
            responseStatusDTO.addErrors("Не указан пароль.");
        }
        if (user.getPassword().length() < 5) {
            responseStatusDTO.setStatus(StatusTypes.ERROR);
            responseStatusDTO.addErrors("Пароль должен быть больше 5 символов.");
        }
        if (user.getEmail().equals("")) {
            responseStatusDTO.setStatus(StatusTypes.ERROR);
            responseStatusDTO.addErrors("Не указан email.");
        }
        return responseStatusDTO;
    }


}

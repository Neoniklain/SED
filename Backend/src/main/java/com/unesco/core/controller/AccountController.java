package com.unesco.core.controller;

import com.unesco.core.managers.account.professorManager.interfaces.professor.IProfessorManager;
import com.unesco.core.managers.account.professorManager.interfaces.professorList.IProfessorListManager;
import com.unesco.core.managers.account.roleManager.interfaces.roleList.IRoleListManager;
import com.unesco.core.managers.account.studentManager.interfaces.student.IStudentManager;
import com.unesco.core.managers.account.userManager.interfaces.user.IUserManager;
import com.unesco.core.managers.account.userManager.interfaces.userList.IUserListManager;
import com.unesco.core.managers.schedule.departmentManager.interfaces.department.IDepartmentManager;
import com.unesco.core.managers.schedule.groupManager.interfaces.group.IGroupManager;
import com.unesco.core.models.account.ProfessorDTO;
import com.unesco.core.models.account.StudentDTO;
import com.unesco.core.models.account.UserDTO;
import com.unesco.core.models.additional.ResponseStatusDTO;
import com.unesco.core.models.enums.RoleType;
import com.unesco.core.services.account.professorService.IProfessorDataService;
import com.unesco.core.services.account.roleService.IRoleDataService;
import com.unesco.core.services.account.studentService.IStudentDataService;
import com.unesco.core.services.account.userService.IUserDataService;
import com.unesco.core.services.schedule.departmentService.IDepartmentDataService;
import com.unesco.core.services.schedule.groupService.IGroupDataService;
import com.unesco.core.utils.StatusTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountController {

    @Autowired
    private IRoleDataService roleDataService;
    @Autowired
    private IRoleListManager roleListManager;

    @Autowired
    private IUserDataService userDataService;
    @Autowired
    private IUserManager userManager;
    @Autowired
    private IUserListManager userListManager;

    @Autowired
    private IProfessorDataService professorDataService;
    @Autowired
    private IProfessorManager professorManager;
    @Autowired
    private IProfessorListManager professorListManager;

    @Autowired
    private IStudentDataService studentDataService;
    @Autowired
    private IStudentManager studentManager;

    @Autowired
    private IDepartmentDataService departmentDataService;
    @Autowired
    private IDepartmentManager departmentManager;

    @Autowired
    private IGroupDataService groupDataService;
    @Autowired
    private IGroupManager groupManager;

    public ResponseStatusDTO GetRoles(UserDTO user) {
        return new ResponseStatusDTO(StatusTypes.OK, user.getRoles());
    }

    public ResponseStatusDTO Registration(UserDTO user) {
        userManager.Create(user, roleDataService.GetAll());
        ResponseStatusDTO res = userManager.Validate();
        if(res.getStatus() != StatusTypes.OK) return res;
        try {
            UserDTO userSaved = userDataService.Save(userManager.Get());

            roleListManager.Init(userSaved.getRoles());

            if (roleListManager.ContainRole(RoleType.PROFESSOR))
            {
                professorManager.Create(userSaved);
                ProfessorDTO professor = professorDataService.Save(professorManager.Get());
            }

            if (roleListManager.ContainRole(RoleType.STUDENT))
            {
                studentManager.Create(userSaved);
                StudentDTO student = studentDataService.Save(studentManager.Get());
            }
            res.setData(userSaved);
            res.addMessage("Пользователь добавлен");
        }
        catch (Exception e) {
            res = new ResponseStatusDTO(StatusTypes.ERROR, e.getMessage());
        }
        return res;
    }

    public ResponseStatusDTO GetUser(UserDTO user) {
        return new ResponseStatusDTO(StatusTypes.OK, user);
    }

    public ResponseStatusDTO ChangePassword(UserDTO user, String newPass, String oldPass) {
        userManager.Init(user);
        ResponseStatusDTO response = userManager.ChangePassword(newPass, oldPass);
        if (response.getStatus() == StatusTypes.ERROR) return response;

        try {
            userDataService.Save(userManager.Get());
            response.addMessage("Пароль изменен.");
        } catch (Exception e) {
            response.setStatus(StatusTypes.ERROR);
            response.addErrors("Не удалось изменить пароль");
        }
        return response;
    }

    public ResponseStatusDTO ChangePhoto(UserDTO user, String photo) {
        userManager.Init(user);
        ResponseStatusDTO response = userManager.ChangePhoto(photo);
        if (response.getStatus() == StatusTypes.ERROR) return response;

        try {
            userDataService.Save(userManager.Get());
            response.addMessage("Фотография изменена.");
        } catch (Exception e) {
            response.setStatus(StatusTypes.ERROR);
            response.addErrors("Не удалось изменить фото");
        }
        return response;
    }

    public ResponseStatusDTO FindUsersByFIO(String req) {
        userListManager.Init(userDataService.GetAll());
        return new ResponseStatusDTO(StatusTypes.OK, userListManager.GetByFio(req));
    }

    public ResponseStatusDTO GetProfessors() {
        professorListManager.Init(professorDataService.GetAll());
        return new ResponseStatusDTO(StatusTypes.OK, professorListManager.GetAll());
    }

    public ResponseStatusDTO GetProfessorByUser(long userId) {
        professorManager.Init(professorDataService.GetByUser(userId));
        return new ResponseStatusDTO(StatusTypes.OK, professorManager.Get());
    }

    public ResponseStatusDTO GetStudentByUser(long userId) {
        studentManager.Init(studentDataService.GetByUser(userId));
        return new ResponseStatusDTO(StatusTypes.OK, studentManager.Get());
    }

    public ResponseStatusDTO setProfessorDepartment(long userId, long departmentId) {
        ResponseStatusDTO res = new ResponseStatusDTO();
        try {
            professorManager.Init(professorDataService.GetByUser(userId));
            departmentManager.Init(departmentDataService.Get(departmentId));
            professorManager.SetDepartment(departmentManager.Get());
            res.setStatus(StatusTypes.OK);
            ProfessorDTO professor = professorDataService.Save(professorManager.Get());
            res.setData(professor);
            res.addMessage("Кафедра для преподавателя установленна");
        }
        catch (Exception e) {
            res.setStatus(StatusTypes.ERROR);
            res.addErrors("Кафедра для преподавателя не установленна");
            res.addErrors(e.toString());
        }
        return res;
    }

    public ResponseStatusDTO FindUserByUsername(String req) {
        userListManager.Init(userDataService.GetAll());
        return new ResponseStatusDTO(StatusTypes.OK, userListManager.GetByUsername(req));
    }

    public ResponseStatusDTO setStudentGroup(long userId, long groupId) {
        studentManager.Init(studentDataService.GetByUser(userId));
        groupManager.Init(groupDataService.Get(groupId));
        studentManager.SetGroup(groupManager.Get());
        ResponseStatusDTO res = new ResponseStatusDTO();
        res.setStatus(StatusTypes.OK);
        try {
            StudentDTO stident = studentDataService.Save(studentManager.Get());
            res.setData(stident);
            res.addMessage("Группа для студента установленна");
            return res;
        }
        catch (Exception e) {
            res.setStatus(StatusTypes.ERROR);
            res.addErrors("Группа для студента не установленна");
            res.addErrors(e.getMessage());
            return res;
        }
    }

}

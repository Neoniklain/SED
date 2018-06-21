package com.unesco.core.controller;

import com.unesco.core.managers.account.professorManager.interfaces.professor.IProfessorManager;
import com.unesco.core.managers.account.professorManager.interfaces.professorList.IProfessorListManager;
import com.unesco.core.managers.account.roleManager.interfaces.roleList.IRoleListManager;
import com.unesco.core.managers.account.studentManager.interfaces.student.IStudentManager;
import com.unesco.core.managers.account.userManager.interfaces.user.IUserManager;
import com.unesco.core.managers.account.userManager.interfaces.userList.IUserListManager;
import com.unesco.core.managers.schedule.departmentManager.interfaces.department.IDepartmentManager;
import com.unesco.core.managers.schedule.groupManager.interfaces.group.IGroupManager;
import com.unesco.core.models.account.ProfessorModel;
import com.unesco.core.models.account.StudentModel;
import com.unesco.core.models.account.UserModel;
import com.unesco.core.models.additional.ResponseStatus;
import com.unesco.core.models.enums.RoleType;
import com.unesco.core.security.CustomUserDetailsService;
import com.unesco.core.services.account.professorService.IProfessorDataService;
import com.unesco.core.services.account.roleService.IRoleDataService;
import com.unesco.core.services.account.studentService.IStudentDataService;
import com.unesco.core.services.account.userService.IUserDataService;
import com.unesco.core.services.schedule.departmentService.IDepartmentDataService;
import com.unesco.core.services.schedule.groupService.IGroupDataService;
import com.unesco.core.utils.StatusTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;

@CrossOrigin
@RestController
@RequestMapping("api/account")
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class AccountController {

    @Autowired
    private CustomUserDetailsService _CustomUserDetailsService;

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

    @GetMapping("/role")
    public ResponseStatus GetRoles() {
        UserModel user = new UserModel(_CustomUserDetailsService.getUserDetails());
        return new ResponseStatus(StatusTypes.OK, user.getRoles());
    }

    @RequestMapping("/registration")
    public ResponseStatus Registration(@RequestBody UserModel user) {
        userManager.Create(user, roleDataService.GetAll());
        ResponseStatus res = userManager.Validate();
        if(res.getStatus() != StatusTypes.OK) return res;
        try {
            UserModel userSaved = userDataService.Save(userManager.Get());

            roleListManager.Init(userSaved.getRoles());

            if (roleListManager.ContainRole(RoleType.PROFESSOR))
            {
                professorManager.Create(userSaved);
                ProfessorModel professor = professorDataService.Save(professorManager.Get());
            }

            if (roleListManager.ContainRole(RoleType.STUDENT))
            {
                studentManager.Create(userSaved);
                StudentModel student = studentDataService.Save(studentManager.Get());
            }
            res.setData(userSaved);
            res.addMessage("Пользователь добавлен");
            return res;
        }
        catch (Exception e) {
            return new ResponseStatus(StatusTypes.ERROR, e.getMessage());
        }
    }

    @GetMapping("/user")
    public ResponseStatus GetUser() {
        UserModel user = new UserModel(_CustomUserDetailsService.getUserDetails());
        return new ResponseStatus(StatusTypes.OK, user);
    }

    @RequestMapping("/changePassword")
    public ResponseStatus ChangePassword(@RequestBody Pass pass) {
        UserModel user = new UserModel(_CustomUserDetailsService.getUserDetails());
        userManager.Init(user);
        ResponseStatus response = userManager.ChangePassword(pass.getNewPass(), pass.getOldPass());
        if (response.getStatus() == StatusTypes.ERROR) return response;

        try {
            userDataService.Save(userManager.Get());
        } catch (Exception e) {
            response.setStatus(StatusTypes.ERROR);
            response.addErrors("Не удалось изменить пароль");
        }
        return response;
    }

    @RequestMapping(value = "/FindUsersByFIO/{req}")
    public ResponseStatus FindUsersByFIO(@PathVariable("req") String req) {
        userListManager.Init(userDataService.GetAll());
        return new ResponseStatus(StatusTypes.OK, userListManager.GetByFio(req));
    }

    @GetMapping("/professors")
    public ResponseStatus GetProfessors() {
        professorListManager.Init(professorDataService.GetAll());
        return new ResponseStatus(StatusTypes.OK, professorListManager.GetAll());
    }

    @GetMapping("/professorByUser/{userId}")
    public ResponseStatus GetProfessorByUser(@PathVariable("userId") int userId) {
        professorManager.Init(professorDataService.GetByUser(userId));
        return new ResponseStatus(StatusTypes.OK, professorManager.Get());
    }

    @GetMapping("/studentByUser/{userId}")
    public ResponseStatus GetStudentByUser(@PathVariable("userId") int userId) {
        studentManager.Init(studentDataService.GetByUser(userId));
        return new ResponseStatus(StatusTypes.OK, studentManager.Get());
    }

    @RequestMapping(value = "/professor/{userId}/setDepartment/{departmentId}")
    public ResponseStatus setProfessorDepartment(@PathVariable("userId") int userId, @PathVariable("departmentId") int departmentId) {
        professorManager.Init(professorDataService.GetByUser(userId));
        departmentManager.Init(departmentDataService.Get(departmentId));
        professorManager.SetDepartment(departmentManager.Get());
        ResponseStatus res = new ResponseStatus();
        res.setStatus(StatusTypes.OK);
        try {
            ProfessorModel professor = professorDataService.Save(professorManager.Get());
            res.setData(professor);
            res.addMessage("Кафедра для преподавателя установленна");
            return res;
        }
        catch (Exception e) {
            res.setStatus(StatusTypes.ERROR);
            res.addErrors("Кафедра для преподавателя не установленна");
            res.addErrors(e.getMessage());
            return res;
        }
    }

    @RequestMapping(value = "/FindUserByUsername/{req}")
    public ResponseStatus FindUserByUsername(@PathVariable("req") String req) {
        userListManager.Init(userDataService.GetAll());
        return new ResponseStatus(StatusTypes.OK, userListManager.GetByUsername(req));
    }

    @RequestMapping(value = "/student/{userId}/setGroup/{groupId}")
    public ResponseStatus setStudentGroup(@PathVariable("userId") int userId, @PathVariable("groupId") int groupId) {
        studentManager.Init(studentDataService.GetByUser(userId));
        groupManager.Init(groupDataService.Get(groupId));
        studentManager.SetGroup(groupManager.Get());
        ResponseStatus res = new ResponseStatus();
        res.setStatus(StatusTypes.OK);
        try {
            StudentModel stident = studentDataService.Save(studentManager.Get());
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

class Pass {
    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

    public String getOldPass() {
        return oldPass;
    }

    public void setOldPass(String oldPass) {
        this.oldPass = oldPass;
    }

    String newPass;
    String oldPass;
}
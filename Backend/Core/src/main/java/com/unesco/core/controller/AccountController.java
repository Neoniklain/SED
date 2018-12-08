package com.unesco.core.controller;

import com.unesco.core.dto.account.UserAccessRightDTO;
import com.unesco.core.dto.account.ProfessorDTO;
import com.unesco.core.dto.account.StudentDTO;
import com.unesco.core.dto.account.UserDTO;
import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.enums.RoleType;
import com.unesco.core.dto.enums.StatusTypes;
import com.unesco.core.dto.journal.StudentJournalList;
import com.unesco.core.managers.account.accessRightManager.interfaces.IAccessRightManager;
import com.unesco.core.managers.account.professorManager.interfaces.professor.IProfessorManager;
import com.unesco.core.managers.account.professorManager.interfaces.professorList.IProfessorListManager;
import com.unesco.core.managers.account.roleManager.interfaces.roleList.IRoleListManager;
import com.unesco.core.managers.account.studentManager.interfaces.student.IStudentManager;
import com.unesco.core.managers.account.studentManager.interfaces.studentList.IStudentListManager;
import com.unesco.core.managers.account.userManager.interfaces.user.IUserManager;
import com.unesco.core.managers.account.userManager.interfaces.userList.IUserListManager;
import com.unesco.core.managers.schedule.departmentManager.interfaces.department.IDepartmentManager;
import com.unesco.core.managers.schedule.groupManager.interfaces.group.IGroupManager;
import com.unesco.core.services.dataService.account.accessRightService.IAccessRightDataService;
import com.unesco.core.services.dataService.account.professorService.IProfessorDataService;
import com.unesco.core.services.dataService.account.roleService.IRoleDataService;
import com.unesco.core.services.dataService.account.studentService.IStudentDataService;
import com.unesco.core.services.dataService.account.userAccessRight.IUserAccessRightDataService;
import com.unesco.core.services.dataService.account.userService.IUserDataService;
import com.unesco.core.services.dataService.schedule.departmentService.IDepartmentDataService;
import com.unesco.core.services.dataService.schedule.groupService.IGroupDataService;
import com.unesco.core.services.userService.IUserService;
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
    private IStudentListManager studentListManager;

    @Autowired
    private IDepartmentDataService departmentDataService;
    @Autowired
    private IDepartmentManager departmentManager;

    @Autowired
    private IGroupDataService groupDataService;
    @Autowired
    private IGroupManager groupManager;

    @Autowired
    private IUserAccessRightDataService userAccessRightDataService;

    @Autowired
    private IAccessRightManager accessRightManager;

    @Autowired
    private IAccessRightDataService accessRightDataService;

    @Autowired
    private IUserService userService;

    public ResponseStatusDTO getRoles() {
        UserDTO user = userService.getCurrentUser();
        if(user != null)
            return new ResponseStatusDTO(StatusTypes.OK, user.getRoles());
        else
            return new ResponseStatusDTO(StatusTypes.OK);
    }

    public ResponseStatusDTO getCurrentUserAccessRight() {
        ResponseStatusDTO res = new ResponseStatusDTO();
        try {
            accessRightManager.init(userAccessRightDataService.get(userService.getCurrentUser().getId()), accessRightDataService.getAll());
            res.setStatus(StatusTypes.OK);
            res.setData(accessRightManager.get());
            return res;
        }
        catch (Exception e) {
            res.setStatus(StatusTypes.ERROR);
            res.addErrors(e.getMessage());
            return res;
        }
    }

    public ResponseStatusDTO registration(UserDTO user) {
        userManager.Create(user, roleDataService.getAll());
        ResponseStatusDTO res = userManager.validate();
        if(res.getStatus() == StatusTypes.ERROR) return res;
        try {
            ResponseStatusDTO<UserDTO> saveUserStatus = userDataService.save(userManager.get());
            if(saveUserStatus.getStatus() == StatusTypes.ERROR) return saveUserStatus;
            UserDTO userSaved = saveUserStatus.getData();

            roleListManager.init(userSaved.getRoles());

            if (roleListManager.ContainRole(RoleType.PROFESSOR))
            {
                professorManager.init(new ProfessorDTO());
                professorManager.create(userSaved);
                ResponseStatusDTO<ProfessorDTO> saveProfessorStatus = professorDataService.save(professorManager.get());
                if (saveProfessorStatus.getStatus() == StatusTypes.ERROR) return saveProfessorStatus;
            }

            if (roleListManager.ContainRole(RoleType.STUDENT))
            {
                studentManager.init(new StudentDTO());
                studentManager.create(userSaved);
                ResponseStatusDTO<StudentDTO> saveStudentStatus = studentDataService.save(studentManager.get());
                if (saveStudentStatus.getStatus() == StatusTypes.ERROR) return saveStudentStatus;
            }
            res.setData(userSaved);
            res.addMessage("Пользователь добавлен");
        }
        catch (Exception e) {
            res = new ResponseStatusDTO(StatusTypes.ERROR, e.getMessage());
        }
        return res;
    }

    public ResponseStatusDTO getUser() {
        UserDTO user = userService.getCurrentUser();
        return new ResponseStatusDTO(StatusTypes.OK, user);
    }

    public ResponseStatusDTO changePassword(String newPass, String oldPass) {
        UserDTO user = userService.getCurrentUser();
        userManager.init(user);
        ResponseStatusDTO response = userManager.ChangePassword(newPass, oldPass);
        if (response.getStatus() == StatusTypes.ERROR) return response;

        try {
            userDataService.save(userManager.get());
            response.addMessage("Пароль изменен.");
        } catch (Exception e) {
            response.setStatus(StatusTypes.ERROR);
            response.addErrors("Не удалось изменить пароль");
        }
        return response;
    }

    public ResponseStatusDTO changePhoto(String photo) {
        UserDTO user = userService.getCurrentUser();
        userManager.init(user);
        ResponseStatusDTO response = userManager.ChangePhoto(photo);
        if (response.getStatus() == StatusTypes.ERROR) return response;

        try {
            userDataService.save(userManager.get());
            response.addMessage("Фотография изменена.");
        } catch (Exception e) {
            response.setStatus(StatusTypes.ERROR);
            response.addErrors("Не удалось изменить фото");
        }
        return response;
    }

    public ResponseStatusDTO findUsersByFIO(String req) {
        userListManager.init(userDataService.getAll());
        return new ResponseStatusDTO(StatusTypes.OK, userListManager.GetByFio(req));
    }

    public ResponseStatusDTO getProfessors() {
        professorListManager.init(professorDataService.getAll());
        return new ResponseStatusDTO(StatusTypes.OK, professorListManager.getAll());
    }

    public ResponseStatusDTO getProfessorByUser(long userId) {
        professorManager.init(professorDataService.getByUser(userId));
        return new ResponseStatusDTO(StatusTypes.OK, professorManager.get());
    }

    public ResponseStatusDTO getStudentByUser(long userId) {
        studentManager.init(studentDataService.getByUser(userId));
        return new ResponseStatusDTO(StatusTypes.OK, studentManager.get());
    }

    public ResponseStatusDTO getStudentForGroupAndLesson(long groupId, long lessonId) {
        return new ResponseStatusDTO(StatusTypes.OK, studentDataService.getByGroupAndLesson(groupId, lessonId));
    }

    public ResponseStatusDTO setProfessorDepartment(long userId, long departmentId) {
        ResponseStatusDTO res = new ResponseStatusDTO();
        try {
            professorManager.init(professorDataService.getByUser(userId));
            departmentManager.init(departmentDataService.get(departmentId));
            professorManager.setDepartment(departmentManager.get());
            res.setStatus(StatusTypes.OK);
            ResponseStatusDTO<ProfessorDTO> saveProfessorStatus = professorDataService.save(professorManager.get());
            if (saveProfessorStatus.getStatus() == StatusTypes.ERROR) return saveProfessorStatus;
            ProfessorDTO professor = saveProfessorStatus.getData();

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

    public ResponseStatusDTO findUserByUsername(String req) {
        userListManager.init(userDataService.getAll());
        return new ResponseStatusDTO(StatusTypes.OK, userListManager.GetByUsername(req));
    }

    public ResponseStatusDTO setStudentGroup(long userId, long groupId) {
        studentManager.init(studentDataService.getByUser(userId));
        groupManager.init(groupDataService.get(groupId));
        studentManager.setGroup(groupManager.get());
        ResponseStatusDTO res = new ResponseStatusDTO();
        res.setStatus(StatusTypes.OK);
        try {
            ResponseStatusDTO<StudentDTO> saveStudentStatus = studentDataService.save(studentManager.get());
            if (saveStudentStatus.getStatus() == StatusTypes.ERROR) return saveStudentStatus;
            StudentDTO student = saveStudentStatus.getData();
            res.setData(student);
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

    public ResponseStatusDTO getUserAccessRight(long userId) {
        ResponseStatusDTO res = new ResponseStatusDTO();
        try {
            accessRightManager.init(userAccessRightDataService.get(userId), accessRightDataService.getAll());
            res.setStatus(StatusTypes.OK);
            res.setData(accessRightManager.get());
            return res;
        }
        catch (Exception e) {
            res.setStatus(StatusTypes.ERROR);
            res.addErrors(e.getMessage());
            return res;
        }
    }

    public ResponseStatusDTO saveUserAccessRight(UserAccessRightDTO acceses) {
        ResponseStatusDTO res = new ResponseStatusDTO();
        accessRightManager.init(acceses, accessRightDataService.getAll());
        res = accessRightManager.validate();
        if(res.getStatus() == StatusTypes.ERROR) return res;
        try {
            userAccessRightDataService.save(acceses);
            res.setStatus(StatusTypes.OK);
            res.addMessage("Настройки доступа сохранены.");
            res.setData(accessRightManager.get());
            return res;
        }
        catch (Exception e) {
            res.setStatus(StatusTypes.ERROR);
            res.addErrors(e.getMessage());
            return res;
        }
    }

    public ResponseStatusDTO saveStudentsSubgroup(StudentJournalList studentJournalList) {
        ResponseStatusDTO res = new ResponseStatusDTO();
        try {
            studentDataService.saveStudentsSubgroup(studentJournalList.getStudentJournal(), studentJournalList.getLesson());
            res.setStatus(StatusTypes.OK);
            res.addMessage("Подгруппы студентов сохранены.");
            res.setData(accessRightManager.get());
            return res;
        }
        catch (Exception e) {
            res.setStatus(StatusTypes.ERROR);
            res.addErrors(e.getMessage());
            return res;
        }
    }

}

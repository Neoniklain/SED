package com.unesco.core.controllerWeb;

import com.unesco.core.controller.AccountController;
import com.unesco.core.dto.account.RoleDTO;
import com.unesco.core.dto.account.UserDTO;
import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.enums.RoleType;
import com.unesco.core.dto.plan.DepartmentDTO;
import com.unesco.core.dto.shedule.GroupDTO;
import com.unesco.core.dto.shedule.InstituteDTO;
import com.unesco.core.services.account.professorService.IProfessorDataService;
import com.unesco.core.services.account.roleService.IRoleDataService;
import com.unesco.core.services.account.studentService.IStudentDataService;
import com.unesco.core.services.account.userService.IUserDataService;
import com.unesco.core.services.schedule.departmentService.IDepartmentDataService;
import com.unesco.core.services.schedule.groupService.IGroupDataService;
import com.unesco.core.services.schedule.instituteService.IInstituteDataService;
import com.unesco.core.utils.StatusTypes;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountControllerWebTest extends Assert {

    @Autowired
    private AccountController accountController;
    @Autowired
    private IRoleDataService roleDataService;
    @Autowired
    private IUserDataService userDataService;
    @Autowired
    private IDepartmentDataService departmentDataService;
    @Autowired
    private IInstituteDataService instituteDataService;
    @Autowired
    private IProfessorDataService professorDataService;
    @Autowired
    private IGroupDataService groupDataService;
    @Autowired
    private IStudentDataService studentDataService;

    private InstituteDTO inst = new InstituteDTO();
    private DepartmentDTO dep = new DepartmentDTO();
    private GroupDTO gr = new GroupDTO();
    private UserDTO testUser = new UserDTO();

    @Before
    public void setUp() throws Exception {

        testUser = getTestUser();
        // Создание преподавателя - профессора
        List<RoleDTO> roles = new ArrayList<>();
        roles.add(roleDataService.GetByName(RoleType.ADMIN.toString()));
        roles.add(roleDataService.GetByName(RoleType.PROFESSOR.toString()));
        roles.add(roleDataService.GetByName(RoleType.STUDENT.toString()));
        testUser.setRoles(roles);
        accountController.Registration(testUser);
        testUser = userDataService.GetByUsername(testUser.getUsername());

        // Создание института
        inst.setName(Math.random()+"");
        instituteDataService.Save(inst);
        inst = instituteDataService.GetByName(inst.getName());

        // Создание департамента
        dep.setName(Math.random()+"");
        dep.setInstitute(inst);
        departmentDataService.Save(dep);
        dep = departmentDataService.GetByName(dep.getName());

        // Создание группы
        gr.setName(Math.random()+"");
        gr.setDepartment(dep);
        groupDataService.Save(gr);
        gr = groupDataService.GetByName(gr.getName());
    }

    @After
    public void tearDown() throws Exception {
        // Удаление студента
        if(professorDataService.GetByUser(userDataService.GetByUsername(testUser.getUsername()).getId())!=null)
            professorDataService.Delete(professorDataService.GetByUser(userDataService.GetByUsername(testUser.getUsername()).getId()).getId());
        // Удаление преподавателя
        if(studentDataService.GetByUser(userDataService.GetByUsername(testUser.getUsername()).getId())!=null)
            studentDataService.Delete(studentDataService.GetByUser(userDataService.GetByUsername(testUser.getUsername()).getId()).getId());
        // Удаление пользователя
        if(userDataService.GetByUsername(testUser.getUsername())!=null)
            userDataService.Delete(userDataService.GetByUsername(testUser.getUsername()).getId());
        // Удаление группы
        groupDataService.Delete(gr.getId());
        // Удаление департамента
        departmentDataService.Delete(dep.getId());
        // Удаление института
        instituteDataService.Delete(inst.getId());
    }

    @Test
    public void getRoles() {
        ResponseStatusDTO resp = new ResponseStatusDTO(StatusTypes.OK);
        try{
            resp = accountController.GetRoles(testUser);
        } catch (Exception e) {
            resp.setStatus(StatusTypes.ERROR);
            resp.addErrors(e.getMessage());
        }
        System.out.println(resp.getErrors());
        System.out.println(resp.getMessage());
        System.out.println(resp.getWarnings());
        assertEquals(resp.getStatus(), StatusTypes.OK);
    }

    @Test
    public void registration() {
        ResponseStatusDTO resp = new ResponseStatusDTO(StatusTypes.OK);
        UserDTO testUser = getTestUser();
        try{
            accountController.Registration(testUser);
        } catch (Exception e) {
            resp.setStatus(StatusTypes.ERROR);
            resp.addErrors(e.getMessage());
        }
        // Удаление пользователя
        if(userDataService.GetByUsername(testUser.getUsername())!=null)
            userDataService.Delete(userDataService.GetByUsername(testUser.getUsername()).getId());
        System.out.println(resp.getErrors());
        System.out.println(resp.getMessage());
        System.out.println(resp.getWarnings());
        assertEquals(resp.getStatus(), StatusTypes.OK);
    }

    @Test
    public void changePassword() {
       ResponseStatusDTO resp = new ResponseStatusDTO(StatusTypes.OK);
        try{
            resp = accountController.ChangePassword(testUser, "123456789", "12345");
        } catch (Exception e) {
            resp.setStatus(StatusTypes.ERROR);
            resp.addErrors(e.getMessage());
        }
        System.out.println(resp.getErrors());
        System.out.println(resp.getMessage());
        System.out.println(resp.getWarnings());
        assertEquals(resp.getStatus(), StatusTypes.OK);
    }

    @Test
    public void changePhoto() {
        ResponseStatusDTO resp = new ResponseStatusDTO(StatusTypes.OK);
        try{
            String photo = "123";
            resp = accountController.ChangePhoto(testUser, photo);
        } catch (Exception e) {
            resp.setStatus(StatusTypes.ERROR);
            resp.addErrors(e.getMessage());
        }
        System.out.println(resp.getErrors());
        System.out.println(resp.getMessage());
        System.out.println(resp.getWarnings());
        assertEquals(resp.getStatus(), StatusTypes.OK);
    }

    @Test
    public void findUsersByFIO() {
        ResponseStatusDTO resp = new ResponseStatusDTO(StatusTypes.OK);
        try{
            resp = accountController.FindUsersByFIO(testUser.getUserFIO());
        } catch (Exception e) {
            resp.setStatus(StatusTypes.ERROR);
            resp.addErrors(e.getMessage());
        }
        System.out.println(resp.getErrors());
        System.out.println(resp.getMessage());
        System.out.println(resp.getWarnings());
        assertEquals(resp.getStatus(), StatusTypes.OK);
    }

    @Test
    public void getProfessors() {
        ResponseStatusDTO resp = new ResponseStatusDTO(StatusTypes.OK);
        try{
            resp = accountController.GetProfessors();
        } catch (Exception e) {
            resp.setStatus(StatusTypes.ERROR);
            resp.addErrors(e.getMessage());
        }
        System.out.println(resp.getErrors());
        System.out.println(resp.getMessage());
        System.out.println(resp.getWarnings());
        assertEquals(resp.getStatus(), StatusTypes.OK);
    }

    @Test
    public void getProfessorByUser() {
        ResponseStatusDTO resp = new ResponseStatusDTO(StatusTypes.OK);
        try{
            resp = accountController.GetProfessorByUser(testUser.getId());
        } catch (Exception e) {
            resp.setStatus(StatusTypes.ERROR);
            resp.addErrors(e.getMessage());
        }
        System.out.println(resp.getErrors());
        System.out.println(resp.getMessage());
        System.out.println(resp.getWarnings());
        assertEquals(resp.getStatus(), StatusTypes.OK);
    }

    @Test
    public void getStudentByUser() {
        ResponseStatusDTO resp = new ResponseStatusDTO(StatusTypes.OK);
        try{
            resp = accountController.GetStudentByUser(testUser.getId());
        } catch (Exception e) {
            resp.setStatus(StatusTypes.ERROR);
            resp.addErrors(e.getMessage());
        }
        System.out.println(resp.getErrors());
        System.out.println(resp.getMessage());
        System.out.println(resp.getWarnings());
        assertEquals(resp.getStatus(), StatusTypes.OK);
    }

    @Test
    public void setProfessorDepartment() {
        ResponseStatusDTO resp = new ResponseStatusDTO(StatusTypes.OK);
        try{
            resp = accountController.setProfessorDepartment(testUser.getId(), dep.getId());

        } catch (Exception e) {
            resp.setStatus(StatusTypes.ERROR);
            resp.addErrors(e.getMessage());
        }
        System.out.println(resp.getErrors());
        System.out.println(resp.getMessage());
        System.out.println(resp.getWarnings());
        assertEquals(resp.getStatus(), StatusTypes.OK);
    }

    @Test
    public void findUserByUsername() {
        ResponseStatusDTO resp = new ResponseStatusDTO(StatusTypes.OK);
        UserDTO testUser = getTestUser();
        try{
            String photo = "123";
            resp = accountController.FindUserByUsername(testUser.getUsername());
        } catch (Exception e) {
            resp.setStatus(StatusTypes.ERROR);
            resp.addErrors(e.getMessage());
        }
        System.out.println(resp.getErrors());
        System.out.println(resp.getMessage());
        System.out.println(resp.getWarnings());
        assertEquals(resp.getStatus(), StatusTypes.OK);
    }

    @Test
    public void setStudentGroup() {

        ResponseStatusDTO resp = new ResponseStatusDTO(StatusTypes.OK);
        try{
            resp = accountController.setStudentGroup(testUser.getId(), gr.getId());
        } catch (Exception e) {
            resp.setStatus(StatusTypes.ERROR);
            resp.addErrors(e.getMessage());
        }
        System.out.println(resp.getErrors());
        System.out.println(resp.getMessage());
        System.out.println(resp.getWarnings());
        assertEquals(resp.getStatus(), StatusTypes.OK);
    }

    UserDTO getTestUser() {
        UserDTO testUser = new UserDTO();
        testUser.setPassword("12345");
        List<RoleDTO> roles = new ArrayList<>();
        roles.add(roleDataService.GetByName(RoleType.ADMIN.toString()));
        testUser.setRoles(roles);
        testUser.setEmail(Math.random()+"@mail.ru");
        testUser.setPhoto("data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBxMTEhISEhMWFRUVFRUVFRUVFRUVFRUVFRUWFhUVFRUYHSggGBolGxUVITEhJSkrLi4uFx8zODMtNygtLisBCgoKDg0OGhAQGy0fHR8tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLSstLy0rLS0tLS0tLS0tLS0tK//AABEIALMBGQMBIgACEQEDEQH/x");
        testUser.setUserFIO("TEst User Testovich");
        testUser.setUsername(Math.random()+"");
        return testUser;
    }
}
package com.unesco.core.controllerWeb;

import com.unesco.core.controller.AccountController;
import com.unesco.core.managers.account.professorManager.interfaces.professor.IProfessorManager;
import com.unesco.core.managers.account.roleManager.interfaces.roleList.IRoleListManager;
import com.unesco.core.managers.account.userManager.interfaces.user.IUserManager;
import com.unesco.core.models.account.ProfessorDTO;
import com.unesco.core.models.account.RoleDTO;
import com.unesco.core.models.account.UserDTO;
import com.unesco.core.models.additional.ResponseStatusDTO;
import com.unesco.core.models.enums.RoleType;
import com.unesco.core.repositories.account.ProfessorRepository;
import com.unesco.core.repositories.account.UserRepository;
import com.unesco.core.services.account.professorService.IProfessorDataService;
import com.unesco.core.services.account.roleService.IRoleDataService;
import com.unesco.core.services.account.userService.IUserDataService;
import com.unesco.core.utils.StatusTypes;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountControllerWebTest {

    @Autowired
    private AccountController accountController;
    @Autowired
    private IRoleDataService roleDataService;
    @Autowired
    private IUserDataService userDataService;
    @Autowired
    private IUserManager userManager;
    @Autowired
    private IProfessorManager professorManager;
    @Autowired
    private IRoleListManager roleListManager;
    @Autowired
    private IProfessorDataService professorDataService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProfessorRepository professorRepository;

    @Test
    public void getRoles() {
        ResponseStatusDTO resp = new ResponseStatusDTO(StatusTypes.OK);
        try{
            resp = accountController.GetRoles();
        } catch (Exception e) {
            resp.setStatus(StatusTypes.ERROR);
        }
        assertEquals(resp.getStatus(), StatusTypes.OK);
    }

    @Test
    public void registration() {
        ResponseStatusDTO resp = new ResponseStatusDTO(StatusTypes.OK);
        try{
            UserDTO testUser = new UserDTO();
            testUser.setPassword("12345");
            List<RoleDTO> roles = new ArrayList<>();
            roles.add(roleDataService.GetByName(RoleType.ADMIN.toString()));
            roles.add(roleDataService.GetByName(RoleType.PROFESSOR.toString()));
            testUser.setRoles(roles);
            testUser.setEmail("testUser@mail.ru");
            testUser.setPhoto("data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBxMTEhISEhMWFRUVFRUVFRUVFRUVFRUVFRUWFhUVFRUYHSggGBolGxUVITEhJSkrLi4uFx8zODMtNygtLisBCgoKDg0OGhAQGy0fHR8tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLSstLy0rLS0tLS0tLS0tLS0tK//AABEIALMBGQMBIgACEQEDEQH/x");
            testUser.setUserFIO("Test User Testovich");
            testUser.setUsername("Testovich");
            userManager.Create(testUser, roleDataService.GetAll());
            resp = userManager.Validate();
            UserDTO userSaved = userDataService.Save(userManager.Get());
            if (roleListManager.ContainRole(RoleType.PROFESSOR))
            {
                professorManager.Create(userSaved);
                ProfessorDTO professor = professorDataService.Save(professorManager.Get());
                professorRepository.delete(professor.getId());
            }
            userRepository.delete(userDataService.GetByUsername("Testovich").getId());
        } catch (Exception e) {
            resp.setStatus(StatusTypes.ERROR);
        }
        assertEquals(resp.getStatus(), StatusTypes.OK);
    }

    @Test
    public void changePassword() {
        ResponseStatusDTO resp = new ResponseStatusDTO(StatusTypes.OK);
        try{

            UserDTO testUser = new UserDTO();
            testUser.setPassword("12345");
            List<RoleDTO> roles = new ArrayList<>();
            roles.add(roleDataService.GetByName(RoleType.ADMIN.toString()));
            roles.add(roleDataService.GetByName(RoleType.PROFESSOR.toString()));
            testUser.setRoles(roles);
            testUser.setEmail("testUser@mail.ru");
            testUser.setPhoto("data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBxMTEhISEhMWFRUVFRUVFRUVFRUVFRUVFRUWFhUVFRUYHSggGBolGxUVITEhJSkrLi4uFx8zODMtNygtLisBCgoKDg0OGhAQGy0fHR8tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLSstLy0rLS0tLS0tLS0tLS0tK//AABEIALMBGQMBIgACEQEDEQH/x");
            testUser.setUserFIO("TEst User Testovich");
            testUser.setUsername("Testovich");
            accountController.Registration(testUser);
            testUser = userDataService.GetByUsername("Testovich");
            userManager.Init(testUser);
            ResponseStatusDTO response = userManager.ChangePassword("123456789", "12345");
            if (response.getStatus() == StatusTypes.ERROR) {
                resp = response;
            } else {
                response.addMessage("Пароль изменен.");
                userDataService.Delete(testUser.getId());
                resp = response;
            }
        } catch (Exception e) {
            resp.setStatus(StatusTypes.ERROR);
        }
        assertEquals(resp.getStatus(), StatusTypes.OK);
    }

    @Test
    public void changePhoto() {
    }

    @Test
    public void findUsersByFIO() {
    }

    @Test
    public void getProfessors() {
    }

    @Test
    public void getProfessorByUser() {
    }

    @Test
    public void getStudentByUser() {
    }

    @Test
    public void setProfessorDepartment() {
    }

    @Test
    public void findUserByUsername() {
    }

    @Test
    public void setStudentGroup() {
    }
}
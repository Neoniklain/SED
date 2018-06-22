package com.unesco.core.controllerWeb;

import com.unesco.core.controller.AccountController;
import com.unesco.core.models.account.UserDTO;
import com.unesco.core.models.additional.ResponseStatusDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;

@CrossOrigin
@RestController
@RequestMapping("api/account")
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class AccountControllerWeb {

    @Autowired
    private AccountController accountController;

    @GetMapping("/role")
    public ResponseStatusDTO GetRoles() {
        return accountController.GetRoles();
    }

    @RequestMapping("/registration")
    public ResponseStatusDTO Registration(@RequestBody UserDTO user) {
        return accountController.Registration(user);
    }

    @GetMapping("/user")
    public ResponseStatusDTO GetUser() {
        return accountController.GetUser();
    }

    @RequestMapping("/changePassword")
    public ResponseStatusDTO ChangePassword(@RequestBody Pass pass) {
        return accountController.ChangePassword(pass.newPass, pass.oldPass);
    }

    @RequestMapping("/changePhoto")
    public ResponseStatusDTO ChangePhoto(@RequestBody String photo) {
        return accountController.ChangePhoto(photo);
    }

    @RequestMapping(value = "/FindUsersByFIO/{req}")
    public ResponseStatusDTO FindUsersByFIO(@PathVariable("req") String req) {
        return accountController.FindUsersByFIO(req);
    }

    @GetMapping("/professors")
    public ResponseStatusDTO GetProfessors() {
        return accountController.GetProfessors();
    }

    @GetMapping("/professorByUser/{userId}")
    public ResponseStatusDTO GetProfessorByUser(@PathVariable("userId") int userId) {
        return accountController.GetProfessorByUser(userId);
    }

    @GetMapping("/studentByUser/{userId}")
    public ResponseStatusDTO GetStudentByUser(@PathVariable("userId") int userId) {
        return accountController.GetStudentByUser(userId);
    }

    @RequestMapping(value = "/professor/{userId}/setDepartment/{departmentId}")
    public ResponseStatusDTO setProfessorDepartment(@PathVariable("userId") int userId, @PathVariable("departmentId") int departmentId) {
        return accountController.setProfessorDepartment(userId, departmentId);
    }

    @RequestMapping(value = "/FindUserByUsername/{req}")
    public ResponseStatusDTO FindUserByUsername(@PathVariable("req") String req) {
        return accountController.FindUserByUsername(req);
    }

    @RequestMapping(value = "/student/{userId}/setGroup/{groupId}")
    public ResponseStatusDTO setStudentGroup(@PathVariable("userId") int userId, @PathVariable("groupId") int groupId) {
        return accountController.setStudentGroup(userId, groupId);
    }

    private class Pass {
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

}

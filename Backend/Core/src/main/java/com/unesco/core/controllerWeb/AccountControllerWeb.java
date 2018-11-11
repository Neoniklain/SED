package com.unesco.core.controllerWeb;

import com.unesco.core.controller.AccountController;
import com.unesco.core.dto.UserAccessRightDTO;
import com.unesco.core.dto.account.UserDTO;
import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.journal.StudentJournalDTO;
import com.unesco.core.dto.journal.StudentJournalList;
import com.unesco.core.dto.shedule.LessonDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/account")
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class AccountControllerWeb {

    @Autowired
    private AccountController accountController;

    @GetMapping("/role")
    public ResponseStatusDTO getRoles() {
        return accountController.getRoles();
    }

    @GetMapping("/userAccessRight")
    public ResponseStatusDTO getCurrentUserAccessRight() {
        return accountController.getCurrentUserAccessRight();
    }

    @RequestMapping("/registration")
    public ResponseStatusDTO registration(@RequestBody UserDTO user) {
        return accountController.registration(user);
    }

    @GetMapping("/user")
    public ResponseStatusDTO getUser() {
        return accountController.getUser();
    }

    @RequestMapping("/changePassword")
    public ResponseStatusDTO changePassword(@RequestBody Pass pass) {
        return accountController.changePassword(pass.newPass, pass.oldPass);
    }

    @RequestMapping("/changePhoto")
    public ResponseStatusDTO changePhoto(@RequestBody String photo) {
        return accountController.changePhoto(photo);
    }

    @RequestMapping(value = "/FindUsersByFIO/{req}")
    public ResponseStatusDTO findUsersByFIO(@PathVariable("req") String req) {
        return accountController.findUsersByFIO(req);
    }

    @GetMapping("/professors")
    public ResponseStatusDTO getProfessors() {
        return accountController.getProfessors();
    }

    @GetMapping("/professorByUser/{userId}")
    public ResponseStatusDTO getProfessorByUser(@PathVariable("userId") long userId) {
        return accountController.getProfessorByUser(userId);
    }

    @GetMapping("/studentByUser/{userId}")
    public ResponseStatusDTO getStudentByUser(@PathVariable("userId") long userId) {
        return accountController.getStudentByUser(userId);
    }

    @GetMapping("/student/group/{groupId}/lesson/{lessonId}")
    public ResponseStatusDTO getStudentForGroupAndLesson(@PathVariable("groupId") long groupId, @PathVariable("lessonId") long lessonId) {
        return accountController.getStudentForGroupAndLesson(groupId, lessonId);
    }

    @RequestMapping(value = "/professor/{userId}/setDepartment/{departmentId}")
    public ResponseStatusDTO setProfessorDepartment(@PathVariable("userId") long userId, @PathVariable("departmentId") long departmentId) {
        return accountController.setProfessorDepartment(userId, departmentId);
    }

    @RequestMapping(value = "/FindUserByUsername/{req}")
    public ResponseStatusDTO findUserByUsername(@PathVariable("req") String req) {
        return accountController.findUserByUsername(req);
    }

    @RequestMapping(value = "/student/{userId}/setGroup/{groupId}")
    public ResponseStatusDTO setStudentGroup(@PathVariable("userId") long userId, @PathVariable("groupId") long groupId) {
        return accountController.setStudentGroup(userId, groupId);
    }

    @RequestMapping(value = "/user/{userId}/getAccessRight")
    public ResponseStatusDTO getUserAccessRight(@PathVariable("userId") long userId) {
        return accountController.getUserAccessRight(userId);
    }

    @RequestMapping(value = "/user/{userId}/saveAccessRight")
    public ResponseStatusDTO saveUserAccessRight(@RequestBody UserAccessRightDTO acceses) {
        return accountController.saveUserAccessRight(acceses);
    }

    @RequestMapping(value = "/student/save/subgroup", method = RequestMethod.POST)
    public ResponseStatusDTO SaveStudentsSubgroup(@RequestBody StudentJournalList studentJournalList) {
        return accountController.saveStudentsSubgroup(studentJournalList);
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

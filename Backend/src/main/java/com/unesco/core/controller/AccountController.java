package com.unesco.core.controller;

import com.unesco.core.models.ProfessorModel;
import com.unesco.core.models.account.UserCreateModel;
import com.unesco.core.models.additional.JSONResponseStatus;
import com.unesco.core.models.account.RoleModel;
import com.unesco.core.models.account.UserModel;
import com.unesco.core.entities.account.Role;
import com.unesco.core.models.enums.RoleType;
import com.unesco.core.repositories.account.RoleRepository;
import com.unesco.core.security.CustomUserDetailsService;
import com.unesco.core.services.dictionaryDataService.DitionaryDataService;
import com.unesco.core.services.dictionaryDataService.IDitionaryDataService;
import com.unesco.core.services.userService.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import com.unesco.core.entities.account.User;
import org.springframework.web.bind.annotation.*;
import com.unesco.core.repositories.account.UserRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping("api/account")
public class AccountController {

    @Autowired
    private IUserService userService;
    @Autowired
    private CustomUserDetailsService _CustomUserDetailsService;
    @Autowired
    private DitionaryDataService ditionaryDataService;

    @GetMapping("/role")
    public List<RoleModel> GetRoles() {
        UserModel user = new UserModel(_CustomUserDetailsService.getUserDetails());
        return user.getRoles();
    }

    @RequestMapping("/registration")
    public String Registration(@RequestBody UserCreateModel user) {
        try {
            int result = userService.AddUser(user);
            return ""+result;
        } catch (Exception e) {
            return "error";
        }
    }

    @GetMapping("/user")
    public UserModel GetUser() {
        UserModel user = new UserModel(_CustomUserDetailsService.getUserDetails());
        return user;
    }

    @RequestMapping(value = "/FindUsersByFIO/{req}")
    public List<UserModel> FindUsersByFIO(@PathVariable("req") String req) {
        List<UserModel> allUsers = userService.getUserByFio(req);
        return allUsers;
    }

    @GetMapping("/professors")
    public List<ProfessorModel> GetProfessors() {
        List<ProfessorModel> professor = userService.getProfessors();
        return professor;
    }

    @RequestMapping(value = "/professor/{userId}/setDepartment/{departmentId}")
    public JSONResponseStatus setProfessorDepartment(@PathVariable("userId") int userId, @PathVariable("departmentId") int departmentId) {
        try {
            userService.setProfessorDepartment(userId, departmentId);
            return JSONResponseStatus.OK();
        } catch (Exception e) {
            return JSONResponseStatus.ERROR();
        }
    }

    @RequestMapping(value = "/FindUserByUsername/{req}")
    public UserModel FindUserByUsername(@PathVariable("req") String req) {
        UserModel res = userService.getUserByUsername(req);
        return res;
    }

    @RequestMapping(value = "/student/{userId}/setGroup/{groupId}")
    public JSONResponseStatus setStudentGroup(@PathVariable("userId") int userId, @PathVariable("groupId") int groupId) {
        try {
            userService.setUserGroup(userId, groupId);
            return JSONResponseStatus.OK();
        } catch (Exception e) {
            return JSONResponseStatus.ERROR();
        }
    }
}
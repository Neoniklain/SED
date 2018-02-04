package com.unesco.core.controller;

import com.unesco.core.ViewModel.JSONResponseStatus;
import com.unesco.core.ViewModel.RoleViewModel;
import com.unesco.core.ViewModel.UserViewModel;
import com.unesco.core.entities.Role;
import com.unesco.core.repositories.RoleRepository;
import com.unesco.core.srvices.CustomUserDetails;
import com.unesco.core.srvices.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import com.unesco.core.entities.User;
import org.springframework.web.bind.annotation.*;
import com.unesco.core.repositories.UserRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping("api/account")
public class AccountController {

    @Autowired
    private CustomUserDetailsService _CustomUserDetailsService;
    @Autowired
    private UserRepository _UserRepository;
    @Autowired
    private RoleRepository _RoleRepository;

    @GetMapping("/role")
    public List<RoleViewModel> GetLast() {
        CustomUserDetails user = _CustomUserDetailsService.getUserDetails();
        List<RoleViewModel> roles = new ArrayList<RoleViewModel>();
        for (Role role: user.getRole()) {
            roles.add(new RoleViewModel(role.getRoleName()));
        }
        return roles;
    }

    @RequestMapping("/registration")
    public String Registration(@RequestBody User user) {
        Set<Role> role = new HashSet<Role>();
        Role r = _RoleRepository.findByRole("USER");
        role.add(r);
        user.setRoles(role);
        _UserRepository.save(user);
        return JSONResponseStatus.OK;
    }

    @GetMapping("/user")
    public UserViewModel GetUser() {
        UserViewModel user = new UserViewModel(_CustomUserDetailsService.getUserDetails());
        return user;
    }

    @RequestMapping(value = "/FindUsersByName/{req}")
    public List<User> FindUsersByName(@PathVariable("req") String req) {
        Iterable<User> allUsers = _UserRepository.findAll();
        List<User> res = new ArrayList<User>();
        for (User item:allUsers) {
            if(item.getUsername().toLowerCase().contains(req.toLowerCase()))
            {
                res.add(item);
            }
        }
        return res;
    }
}
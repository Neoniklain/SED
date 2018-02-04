package com.unesco.core.controller;

import com.unesco.core.srvices.CustomUserDetails;
import com.unesco.core.srvices.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import com.unesco.core.entities.User;
import org.springframework.web.bind.annotation.*;
import com.unesco.core.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/account")
public class AccountController {

    @Autowired
    private CustomUserDetailsService _CustomUserDetailsService;
    @Autowired
    private UserRepository _UserRepository;

    @GetMapping("/role")
    public String GetLast() {
        CustomUserDetails user = _CustomUserDetailsService.getUserDetails();
        return user.getRole();
    }

    @GetMapping("/registration")
    public String Registration() {
        CustomUserDetails user = _CustomUserDetailsService.getUserDetails();
        return user.getRole();
    }

    @GetMapping("/user")
    public CustomUserDetails GetUser() {
        return _CustomUserDetailsService.getUserDetails();
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
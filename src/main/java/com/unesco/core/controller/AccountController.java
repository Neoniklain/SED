package com.unesco.core.controller;

import com.unesco.core.entities.User;
import com.unesco.core.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("api/account")
public class AccountController {
    @Autowired
    private UsersRepository _UserRepository;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public User LogIn(@RequestBody User user) {
        User temp = _UserRepository.findByUsername(user.getUsername());
        if(temp.getPassword().equals(user.getPassword())) {
            return temp;
        }
        else
        {
            return null;
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String RegisterNewUser(@RequestBody User user) {
        try {
            _UserRepository.save(user);
            return "OK";
        }
        catch (Exception ex)
        {
            return "Not OK";
        }
    }
}

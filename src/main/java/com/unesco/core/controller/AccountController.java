package com.unesco.core.controller;

import com.unesco.core.entities.User;
import com.unesco.core.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/Account")
public class AccountController {
    @Autowired
    private UsersRepository _UserRepository;

    @GetMapping("")
    public String getAccountPage(Model model) {
        try {
            User user = _UserRepository.findOne((long) 1);
            model.addAttribute("username", user.getUsername());
            if(!user.getRoles().isEmpty())
            {
                model.addAttribute("role", user.getRoles().toArray()[0]);
            }
            else
            {
                model.addAttribute("role", "role is empty");
            }
        }
        catch (Exception ex)
        {
            model.addAttribute("username","Cannot get user");
            model.addAttribute("role","Cannot get user");
        }
        return "lk";
    }

    @RequestMapping(value = "/login",
            method = RequestMethod.GET)
    public String getLoginPage() {
        return "login";
    }

    @RequestMapping(value = "/login",
            method = RequestMethod.POST)
    public ModelAndView getLoginPage(User user) {
        try {
            User temp = _UserRepository.findByUsername(user.getUsername());
            if(temp!=null) {
                if(temp.getPassword().equals(user.getPassword()))
                {
                    ModelAndView MV = new ModelAndView("lk");
                    MV.addObject("username",user.getUsername());
                    MV.addObject("role","Пока без роли (из контроллера)");
                    return MV;
                }
                else
                    return new ModelAndView("login");
            }
            else
                return new ModelAndView("login");
        }
        catch (Exception ex)
        {
            return new ModelAndView("login");
        }
    }

    @RequestMapping(value = "/register" ,method = RequestMethod.GET)
    public String getRegisterPage() {
        return "register";
    }

    @RequestMapping(value = "/register",
                    method = RequestMethod.POST)
    public String RegisterNewUser(User user) {
        try {
            _UserRepository.save(user);
            return getLoginPage();
        }
        catch (Exception ex)
        {
            return getRegisterPage();
        }
    }
}

package com.unesco.core.controller;

import com.unesco.core.entities.News;
import com.unesco.core.repositories.NewsRepository;
import com.unesco.core.srvices.CustomUserDetails;
import com.unesco.core.srvices.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by lukasz on 27.08.2017.
 * With IntelliJ IDEA 15
 */
@RestController
@RequestMapping("api/account")
public class AccountController {

    @Autowired
    private CustomUserDetailsService _CustomUserDetailsService;

    @GetMapping("/role")
    public String GetLast() {
        CustomUserDetails user = _CustomUserDetailsService.getUserDetails();
        return user.getRole();
    }
}
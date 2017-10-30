package com.unesco.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by lukasz on 27.08.2017.
 * With IntelliJ IDEA 15
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String getIndexPage() {
        return "index";
    }

    @GetMapping("/editor")
    public String getEditorPage() {
        return "editor_news";
    }

    @GetMapping("/account")
    public String getAccountPage() {
        return "lk";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/registr")
    public String getRegistrPage() {
        return "registr";
    }
}

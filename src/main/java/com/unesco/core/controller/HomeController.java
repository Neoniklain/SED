package com.unesco.core.controller;

import com.unesco.core.entities.News;
import com.unesco.core.repositories.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by lukasz on 27.08.2017.
 * With IntelliJ IDEA 15
 */
@Controller
public class HomeController {

    @Autowired
    private NewsRepository _NewsRepository;

    @GetMapping("/")
    public ModelAndView getIndexPage() {
        ModelAndView mav = new ModelAndView("index");
        Iterable<News> news = _NewsRepository.findAll();
        List<News> array = StreamSupport.stream(news.spliterator(), false).collect(Collectors.toList());
        Collections.reverse(array);
        mav.addObject("News", array);
        return mav;
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

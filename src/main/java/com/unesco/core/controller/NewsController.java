package com.unesco.core.controller;

import com.unesco.core.entities.News;
import com.unesco.core.repositories.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;
import java.util.Date;

/**
 * Created by lukasz on 27.08.2017.
 * With IntelliJ IDEA 15
 */
@Controller
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsRepository _NewsRepository;

    @GetMapping("/editor")
    public ModelAndView getEditorPage() {
        ModelAndView mav = new ModelAndView("editor_news");
        News news = new News();
        Date day = new Date();
        news.setDate(day);
        mav.addObject("News", news);
        return mav;
    }

    @GetMapping("/account")
    public String getAccountPage() {
        return "lk";
    }

    @RequestMapping(value="/create", method=RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public News AddNews(@RequestBody News news) {
        _NewsRepository.save(news);
        return news;
    }

}

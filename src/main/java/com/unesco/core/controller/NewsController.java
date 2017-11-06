package com.unesco.core.controller;

import com.unesco.core.entities.News;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * Created by lukasz on 27.08.2017.
 * With IntelliJ IDEA 15
 */
@Controller
@RequestMapping("/news")
public class NewsController {

    @GetMapping("/editor")
    public ModelAndView getEditorPage() {
        ModelAndView mav = new ModelAndView("editor_news");
        mav.addObject("News", new News());
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
        return news;
    }

}

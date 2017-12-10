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
@RequestMapping("api/news")
public class NewsController {

    @Autowired
    private NewsRepository _NewsRepository;

    @GetMapping("/getall")
    public List<News> GetAllNews() {
        Iterable<News> news = _NewsRepository.findAll();
        List<News> array = StreamSupport.stream(news.spliterator(), false).collect(Collectors.toList());
        Collections.reverse(array);
        return array;
    }

    @GetMapping("/getlast")
    public News GetLast() {
        News news = _NewsRepository.findTop1ByOrderByDateDesc();
        return news;
    }

    @RequestMapping(value = "/get/{id}")
    public News Get(@PathVariable("id") long id) {
        News news = _NewsRepository.findById(id);
        return news;
    }

    @RequestMapping(value = "/delete/{id}")
    public String Delete(@PathVariable("id") long id) {
        _NewsRepository.delete(id);
        return "OK";
    }

    @RequestMapping(value = "/save")
    public String save(@RequestBody News news) {
        if(_NewsRepository.findById(news.getId())!=null) {

        }else{
            Date day = new Date();
            news.setDate(day);
        }
        _NewsRepository.save(news);
        return "OK";
    }
}

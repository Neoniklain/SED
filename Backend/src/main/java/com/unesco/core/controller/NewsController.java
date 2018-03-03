package com.unesco.core.controller;

import com.unesco.core.models.additional.JSONResponseStatus;
import com.unesco.core.entities.news.News;
import com.unesco.core.repositories.news.NewsRepository;
import com.unesco.core.repositories.account.UserRepository;
import com.unesco.core.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by lukasz on 27.08.2017.
 * With IntelliJ IDEA 15
 */
@CrossOrigin
@RestController
@RequestMapping("api/news")
public class NewsController {

    @Autowired
    private UserRepository _UserRepository;
    @Autowired
    private NewsRepository _NewsRepository;
    @Autowired
    private CustomUserDetailsService _CustomUserDetailsService;

    @GetMapping("/all")
    public List<News> GetAllNews() {
        Iterable<News> news = _NewsRepository.findAll();
        List<News> array = StreamSupport.stream(news.spliterator(), false).collect(Collectors.toList());
        Collections.reverse(array);
        return array;
    }

    @GetMapping("/last")
    public News GetLast() {
        UserDetails user = _CustomUserDetailsService.getUserDetails();
        News news = _NewsRepository.findTop1ByOrderByDateDesc();
        return news == null ? new News(): news;
    }

    @RequestMapping(value = "/get/{id}")
    public News Get(@PathVariable("id") long id) {
        News news = _NewsRepository.findById(id);
        return news;
    }

    @RequestMapping(value = "/delete/{id}")
    public String Delete(@PathVariable("id") long id) {
        _NewsRepository.delete(id);
        return JSONResponseStatus.OK;
    }

    @RequestMapping(value = "/save")
    public String save(@RequestBody News news) {
        if(_NewsRepository.findById(news.getId())!=null) {

        }else{
            Date day = new Date();
            news.setDate(day);
            UserDetails user = _CustomUserDetailsService.getUserDetails();
            news.setAuthor(_UserRepository.findByUsername(user.getUsername()));
        }
        _NewsRepository.save(news);
        return JSONResponseStatus.OK;
    }
}

package com.unesco.core.controller;

import com.unesco.core.managers.news.newsManager.interfaces.news.INewsManager;
import com.unesco.core.managers.news.newsManager.interfaces.newsList.INewsListManager;
import com.unesco.core.models.additional.ResponseStatus;
import com.unesco.core.models.news.NewsModel;
import com.unesco.core.security.CustomUserDetailsService;
import com.unesco.core.services.account.userService.IUserDataService;
import com.unesco.core.services.news.newsService.INewsDataService;
import com.unesco.core.utils.StatusTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Created by lukasz on 27.08.2017.
 * With IntelliJ IDEA 15
 */
@CrossOrigin
@RestController
@RequestMapping("api/news")
public class NewsController {

    @Autowired
    private INewsDataService newsDataService;
    @Autowired
    private IUserDataService userDataService;
    @Autowired
    private INewsManager newsManager;
    @Autowired
    private INewsListManager newsListManager;

    @Autowired
    private CustomUserDetailsService _CustomUserDetailsService;

    @GetMapping("/all")
    public ResponseStatus GetAllNews() {
        newsListManager.Init(newsDataService.GetAll());
        return new ResponseStatus(StatusTypes.OK, newsListManager.GetAll());
    }

    @GetMapping("/last")
    public ResponseStatus GetLast() {
        newsListManager.Init(newsDataService.GetAll());
        return new ResponseStatus(StatusTypes.OK, newsListManager.GetLast());
    }

    @RequestMapping(value = "/get/{id}")
    public ResponseStatus Get(@PathVariable("id") long id) {
        newsManager.Init(newsDataService.Get(id));
        return new ResponseStatus(StatusTypes.OK, newsManager.Get());
    }

    @RequestMapping(value = "/delete/{id}")
    public ResponseStatus Delete(@PathVariable("id") long id) {
        try {
            newsDataService.Delete(id);
            return new ResponseStatus(StatusTypes.OK);
        }
        catch (Exception e) {
            return new ResponseStatus(StatusTypes.ERROR, e.getMessage());
        }
    }

    @RequestMapping(value = "/save")
    public ResponseStatus save(@RequestBody NewsModel news) {
        newsManager.Init(news);
        ResponseStatus result = newsManager.Validate();
        if(result.getStatus() != StatusTypes.OK) return result;
        try {
            Date day = new Date();
            news.setDate(day);
            UserDetails user = _CustomUserDetailsService.getUserDetails();
            news.setAuthor(userDataService.GetByUsername(user.getUsername()));
            newsDataService.Save(news);
            return new ResponseStatus(StatusTypes.OK);
        }
        catch (Exception e) {
            return new ResponseStatus(StatusTypes.ERROR, e.getMessage());
        }
    }
}

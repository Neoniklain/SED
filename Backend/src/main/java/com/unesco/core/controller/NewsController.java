package com.unesco.core.controller;

import com.unesco.core.managers.news.newsManager.interfaces.news.INewsManager;
import com.unesco.core.managers.news.newsManager.interfaces.newsList.INewsListManager;
import com.unesco.core.models.additional.ResponseStatusDTO;
import com.unesco.core.models.news.NewsDTO;
import com.unesco.core.security.CustomUserDetailsService;
import com.unesco.core.services.account.userService.IUserDataService;
import com.unesco.core.services.news.newsService.INewsDataService;
import com.unesco.core.utils.StatusTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
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

    public ResponseStatusDTO GetAllNews() {
        newsListManager.Init(newsDataService.GetAll());
        newsListManager.SortDesc();
        return new ResponseStatusDTO(StatusTypes.OK, newsListManager.GetAll());
    }

    public ResponseStatusDTO GetLast() {
        newsListManager.Init(newsDataService.GetAll());
        newsListManager.SortDesc();
        return new ResponseStatusDTO(StatusTypes.OK, newsListManager.GetLast());
    }

    public ResponseStatusDTO Get(long id) {
        newsManager.Init(newsDataService.Get(id));
        return new ResponseStatusDTO(StatusTypes.OK, newsManager.Get());
    }

    public ResponseStatusDTO Delete(long id) {
        try {
            newsDataService.Delete(id);
            return new ResponseStatusDTO(StatusTypes.OK);
        }
        catch (Exception e) {
            return new ResponseStatusDTO(StatusTypes.ERROR, e.getMessage());
        }
    }

    public ResponseStatusDTO Save(NewsDTO news) {
        newsManager.Init(news);
        ResponseStatusDTO result = newsManager.Validate();
        if(result.getStatus() != StatusTypes.OK) return result;
        try {
            Date day = new Date();
            news.setDate(day);
            UserDetails user = _CustomUserDetailsService.getUserDetails();
            news.setAuthor(userDataService.GetByUsername(user.getUsername()));
            newsDataService.Save(news);
            return new ResponseStatusDTO(StatusTypes.OK);
        }
        catch (Exception e) {
            return new ResponseStatusDTO(StatusTypes.ERROR, e.getMessage());
        }
    }
}

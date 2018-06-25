package com.unesco.core.controller;

import com.unesco.core.managers.news.newsManager.interfaces.news.INewsManager;
import com.unesco.core.managers.news.newsManager.interfaces.newsList.INewsListManager;
import com.unesco.core.dto.account.UserDTO;
import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.news.NewsDTO;
import com.unesco.core.services.account.userService.IUserDataService;
import com.unesco.core.services.news.newsService.INewsDataService;
import com.unesco.core.utils.StatusTypes;
import org.springframework.beans.factory.annotation.Autowired;
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

    public ResponseStatusDTO Save(UserDTO user, NewsDTO news) {
        Date day = new Date();
        news.setDate(day);
        news.setAuthor(user);
        newsManager.Init(news);
        ResponseStatusDTO result = newsManager.Validate();
        if(result.getStatus() != StatusTypes.OK) return result;
        try {
            newsDataService.Save(news);
            return new ResponseStatusDTO(StatusTypes.OK);
        }
        catch (Exception e) {
            return new ResponseStatusDTO(StatusTypes.ERROR, e.getMessage());
        }
    }
}

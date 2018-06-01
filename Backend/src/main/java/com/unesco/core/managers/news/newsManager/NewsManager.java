package com.unesco.core.managers.news.newsManager;

import com.unesco.core.managers.news.newsManager.interfaces.news.INewsManager;
import com.unesco.core.models.additional.ResponseStatus;
import com.unesco.core.models.news.NewsModel;
import com.unesco.core.services.news.newsService.INewsDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsManager implements INewsManager {

    public NewsModel news;

    public NewsManager() {
        news = new NewsModel();
    }

    public void Init(NewsModel model) {
        news = model;
    }

    public NewsModel Get() {
        return news;
    }
}

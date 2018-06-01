package com.unesco.core.managers.news.newsManager;

import com.unesco.core.managers.news.newsManager.interfaces.newsList.INewsListManager;
import com.unesco.core.models.news.NewsModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewsListManager implements INewsListManager {

    public List<NewsModel> newsList;

    public NewsListManager() {
        newsList = new ArrayList<>();
    }

    public void Init(List<NewsModel> NewsList) {
        newsList = NewsList;
    }

    public List<NewsModel> GetAll() {
        return newsList;
    }

    public NewsModel GetLast() {
        NewsModel last = new NewsModel();
        if(newsList.size()>0) {
            last = newsList.stream().findFirst().get();
            for (NewsModel n :newsList) {
                if (last.getDate().compareTo(n.getDate()) > 0) {
                    last = n;
                }
            }
        }
        return last;
    }
}

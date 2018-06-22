package com.unesco.core.managers.news.newsManager;

import com.unesco.core.managers.news.newsManager.interfaces.newsList.INewsListManager;
import com.unesco.core.models.news.NewsModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
        SortDesc();
        last = newsList.get(0);
        return last;
    }

    public void SortDesc() {
        Collections.sort(newsList, new Comparator<NewsModel>() {
            public int compare(NewsModel o1, NewsModel o2) {
                return o2.getDate().compareTo(o1.getDate());
            }
        });
    }
}

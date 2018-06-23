package com.unesco.core.managers.news.newsManager;

import com.unesco.core.managers.news.newsManager.interfaces.newsList.INewsListManager;
import com.unesco.core.models.news.NewsDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class NewsListManager implements INewsListManager {

    public List<NewsDTO> newsList;

    public NewsListManager() {
        newsList = new ArrayList<>();
    }

    public void Init(List<NewsDTO> NewsList) {
        newsList = NewsList;
    }

    public List<NewsDTO> GetAll() {
        return newsList;
    }

    public NewsDTO GetLast() {
        NewsDTO last = new NewsDTO();
        SortDesc();
        if(!newsList.isEmpty())
            last = newsList.get(0);
        return last;
    }

    public void SortDesc() {
        if(!newsList.isEmpty())
            Collections.sort(newsList, new Comparator<NewsDTO>() {
                public int compare(NewsDTO o1, NewsDTO o2) {
                return o2.getDate().compareTo(o1.getDate());
            }
            });
    }
}

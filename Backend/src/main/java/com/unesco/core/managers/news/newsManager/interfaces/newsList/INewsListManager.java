package com.unesco.core.managers.news.newsManager.interfaces.newsList;

import com.unesco.core.managers.IListManager;
import com.unesco.core.models.news.NewsDTO;

public interface INewsListManager extends IListManager<NewsDTO> {
    NewsDTO GetLast();
    void SortDesc();
}

package com.unesco.core.managers.news.newsManager.interfaces.newsList;

import com.unesco.core.managers.IListManager;
import com.unesco.core.models.news.NewsModel;

public interface INewsListManager extends IListManager<NewsModel> {
    NewsModel GetLast();
}

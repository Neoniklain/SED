package com.unesco.core.services.news.newsService;

import com.unesco.core.models.additional.FilterQuery;
import com.unesco.core.models.news.NewsModel;
import com.unesco.core.services.IDataService;

import java.util.List;

public interface INewsDataService extends IDataService<NewsModel> {
    List<NewsModel> GetPage(FilterQuery filter);
}

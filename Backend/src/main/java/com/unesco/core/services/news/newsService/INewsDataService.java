package com.unesco.core.services.news.newsService;

import com.unesco.core.models.additional.FilterQueryDTO;
import com.unesco.core.models.news.NewsDTO;
import com.unesco.core.services.IDataService;

import java.util.List;

public interface INewsDataService extends IDataService<NewsDTO> {
    List<NewsDTO> GetPage(FilterQueryDTO filter);
}

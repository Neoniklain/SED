package com.unesco.core.services.news.newsService;

import com.unesco.core.dto.additional.FilterQueryDTO;
import com.unesco.core.dto.news.NewsDTO;
import com.unesco.core.services.IDataService;

import java.util.List;

public interface INewsDataService extends IDataService<NewsDTO> {
    List<NewsDTO> GetPage(FilterQueryDTO filter);
}

package com.unesco.core.services.dataService.newsService;

import com.unesco.core.dto.additional.FilterQueryDTO;
import com.unesco.core.dto.news.NewsDTO;
import com.unesco.core.services.dataService.IDataService;

import java.util.List;

public interface INewsDataService extends IDataService<NewsDTO> {
    List<NewsDTO> getPage(FilterQueryDTO filter);
}

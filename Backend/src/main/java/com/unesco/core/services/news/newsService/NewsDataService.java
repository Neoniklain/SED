package com.unesco.core.services.news.newsService;

import com.unesco.core.entities.news.News;
import com.unesco.core.models.news.NewsModel;
import com.unesco.core.models.additional.FilterQuery;
import com.unesco.core.repositories.news.NewsRepository;
import com.unesco.core.services.mapperService.IMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewsDataService implements INewsDataService {

    @Autowired
    private IMapperService mapperService;
    @Autowired
    private NewsRepository newsRepository;

    public List<NewsModel> GetPage(FilterQuery filter) {
        int rows = filter.getRows()>0? filter.getRows() : (int) newsRepository.count();
        int start = rows>0 ? filter.getFirst()/rows: 1;
        List<News> entitys = newsRepository.findWithFilter(new PageRequest(start, rows == 0 ? 10 : rows), filter.getGlobalFilter());
        List<NewsModel> result = new ArrayList<>();
        for (News e: entitys) {
            result.add((NewsModel) mapperService.toModel(e));
        }
        return result;
    }

    public List<NewsModel> GetAll()
    {
        List<NewsModel> modelList = new ArrayList<>();
        Iterable<News> entityList = newsRepository.findAll();
        for (News item: entityList) {
            NewsModel model = (NewsModel) mapperService.toModel(item);
            modelList.add(model);
        }
        return modelList;
    }

    public NewsModel Get(long id)
    {
        News entity = newsRepository.findOne(id);
        NewsModel model = (NewsModel) mapperService.toModel(entity);
        return model;
    }

    public void Delete(long id)
    {
        newsRepository.delete(id);
    }

    public NewsModel Save(NewsModel news)
    {
        News entity = (News) mapperService.toEntity(news);
        entity = newsRepository.save(entity);
        news = (NewsModel) mapperService.toModel(entity);
        return news;
    }

    public NewsModel GetLast()
    {
        News entity = newsRepository.findTop1ByOrderByDateDesc();
        return (NewsModel) mapperService.toModel(entity);
    }
}

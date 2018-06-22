package com.unesco.core.services.news.newsService;

import com.unesco.core.entities.news.NewsEntity;
import com.unesco.core.models.news.NewsDTO;
import com.unesco.core.models.additional.FilterQueryDTO;
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

    public List<NewsDTO> GetPage(FilterQueryDTO filter) {
        int rows = filter.getRows()>0? filter.getRows() : (int) newsRepository.count();
        int start = rows>0 ? filter.getFirst()/rows: 1;
        List<NewsEntity> entitys = newsRepository.findWithFilter(new PageRequest(start, rows == 0 ? 10 : rows), filter.getGlobalFilter());
        List<NewsDTO> result = new ArrayList<>();
        for (NewsEntity e: entitys) {
            result.add((NewsDTO) mapperService.toModel(e));
        }
        return result;
    }

    public List<NewsDTO> GetAll()
    {
        List<NewsDTO> modelList = new ArrayList<>();
        Iterable<NewsEntity> entityList = newsRepository.findAll();
        for (NewsEntity item: entityList) {
            NewsDTO model = (NewsDTO) mapperService.toModel(item);
            modelList.add(model);
        }
        return modelList;
    }

    public NewsDTO Get(long id)
    {
        NewsEntity entity = newsRepository.findOne(id);
        NewsDTO model = (NewsDTO) mapperService.toModel(entity);
        return model;
    }

    public void Delete(long id)
    {
        newsRepository.delete(id);
    }

    public NewsDTO Save(NewsDTO news)
    {
        NewsEntity entity = (NewsEntity) mapperService.toEntity(news);
        entity = newsRepository.save(entity);
        news = (NewsDTO) mapperService.toModel(entity);
        return news;
    }

    public NewsDTO GetLast()
    {
        NewsEntity entity = newsRepository.findTop1ByOrderByDateDesc();
        return (NewsDTO) mapperService.toModel(entity);
    }
}

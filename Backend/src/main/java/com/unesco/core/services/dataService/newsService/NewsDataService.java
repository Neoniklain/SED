package com.unesco.core.services.dataService.newsService;

import com.unesco.core.dto.additional.FilterQueryDTO;
import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.enums.StatusTypes;
import com.unesco.core.dto.news.NewsDTO;
import com.unesco.core.entities.news.NewsEntity;
import com.unesco.core.repositories.news.NewsRepository;
import com.unesco.core.services.dataService.mapperService.IMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

    public List<NewsDTO> getPage(FilterQueryDTO filter) {
        int rows = filter.getRows()>0? filter.getRows() : (int) newsRepository.count();
        int start = rows>0 ? filter.getFirst()/rows: 1;
        List<NewsEntity> entitys = newsRepository.findWithFilter(new PageRequest(start, rows == 0 ? 10 : rows), filter.getGlobalFilter());
        List<NewsDTO> result = new ArrayList<>();
        for (NewsEntity e: entitys) {
            result.add((NewsDTO) mapperService.toDto(e));
        }
        return result;
    }

    public List<NewsDTO> getAll()
    {
        List<NewsDTO> modelList = new ArrayList<>();
        Iterable<NewsEntity> entityList = newsRepository.findAll();
        for (NewsEntity item: entityList) {
            NewsDTO model = (NewsDTO) mapperService.toDto(item);
            modelList.add(model);
        }
        return modelList;
    }

    public NewsDTO get(long id)
    {
        NewsEntity entity = newsRepository.findOne(id);
        NewsDTO model = (NewsDTO) mapperService.toDto(entity);
        return model;
    }

    public ResponseStatusDTO<NewsDTO> delete(long id)
    {
        ResponseStatusDTO<NewsDTO> result = new ResponseStatusDTO<>(StatusTypes.OK);
        try {
            newsRepository.delete(id);
        } catch (Exception e) {
            result.setStatus(StatusTypes.ERROR);
            if(e instanceof DataIntegrityViolationException)
                result.addErrors("Удаление не удалось. У объекта есть зависимости.");
            result.addErrors("Удаление не удалось");
            return result;
        }
        return result;
    }

    public ResponseStatusDTO<NewsDTO> save(NewsDTO news)
    {
        NewsEntity entity = (NewsEntity) mapperService.toEntity(news);
        ResponseStatusDTO<NewsDTO> result = new ResponseStatusDTO<>(StatusTypes.OK);
        try {
            entity = newsRepository.save(entity);
        } catch (Exception e) {
            result.setStatus(StatusTypes.ERROR);
            result.addErrors(e.getMessage());
            return result;
        }
        result.setData((NewsDTO) mapperService.toDto(entity));
        return result;
    }

    public NewsDTO getLast()
    {
        NewsEntity entity = newsRepository.findTop1ByOrderByDateDesc();
        return (NewsDTO) mapperService.toDto(entity);
    }
}

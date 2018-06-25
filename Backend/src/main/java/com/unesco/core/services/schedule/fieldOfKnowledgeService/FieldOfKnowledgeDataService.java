package com.unesco.core.services.schedule.fieldOfKnowledgeService;

import com.unesco.core.entities.schedule.FieldOfKnowledgeEntity;
import com.unesco.core.dto.additional.FilterQueryDTO;
import com.unesco.core.dto.shedule.FieldOfKnowledgeDTO;
import com.unesco.core.repositories.plan.FieldOfKnowledgeRepository;
import com.unesco.core.services.mapperService.IMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FieldOfKnowledgeDataService implements IFieldOfKnowledgeDataService {

    @Autowired
    private IMapperService mapperService;
    @Autowired
    private FieldOfKnowledgeRepository fieldOfKnowledgeRepository;

    public List<FieldOfKnowledgeDTO> GetPage(FilterQueryDTO filter) {
        int rows = filter.getRows()>0? filter.getRows() : (int) fieldOfKnowledgeRepository.count();
        int start = rows>0 ? filter.getFirst()/rows: 1;
        List<FieldOfKnowledgeEntity> entitys = fieldOfKnowledgeRepository.findWithFilter(new PageRequest(start, rows == 0 ? 10 : rows), filter.getGlobalFilter());
        List<FieldOfKnowledgeDTO> result = new ArrayList<FieldOfKnowledgeDTO>();
        for (FieldOfKnowledgeEntity e: entitys) {
            result.add((FieldOfKnowledgeDTO) mapperService.toDto(e));
        }
        return result;
    }

    public List<FieldOfKnowledgeDTO> GetAll()
    {
        List<FieldOfKnowledgeDTO> modelList = new ArrayList<>();
        Iterable<FieldOfKnowledgeEntity> entityList = fieldOfKnowledgeRepository.findAll();
        for (FieldOfKnowledgeEntity item: entityList) {
            FieldOfKnowledgeDTO model = (FieldOfKnowledgeDTO) mapperService.toDto(item);
            modelList.add(model);
        }
        return modelList;
    }

    public FieldOfKnowledgeDTO Get(long id)
    {
        FieldOfKnowledgeEntity entity = fieldOfKnowledgeRepository.findOne(id);
        FieldOfKnowledgeDTO model = (FieldOfKnowledgeDTO) mapperService.toDto(entity);
        return model;
    }

    public FieldOfKnowledgeDTO GetByName(String name)
    {
        FieldOfKnowledgeEntity entity = fieldOfKnowledgeRepository.findByName(name);
        FieldOfKnowledgeDTO model = (FieldOfKnowledgeDTO) mapperService.toDto(entity);
        return model;
    }

    public void Delete(long id)
    {
        fieldOfKnowledgeRepository.delete(id);
    }

    public FieldOfKnowledgeDTO Save(FieldOfKnowledgeDTO fieldOfKnowledge)
    {
        FieldOfKnowledgeEntity entity = (FieldOfKnowledgeEntity) mapperService.toEntity(fieldOfKnowledge);
        FieldOfKnowledgeEntity model = fieldOfKnowledgeRepository.save(entity);
        fieldOfKnowledge = (FieldOfKnowledgeDTO) mapperService.toDto(model);
        return fieldOfKnowledge;
    }
}

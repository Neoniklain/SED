package com.unesco.core.services.schedule.fieldOfKnowledgeService;

import com.unesco.core.entities.schedule.FieldOfKnowledge;
import com.unesco.core.models.shedule.FieldOfKnowledgeModel;
import com.unesco.core.models.additional.FilterQuery;
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

    public List<FieldOfKnowledgeModel> GetPage(FilterQuery filter) {
        int rows = filter.getRows()>0? filter.getRows() : (int) fieldOfKnowledgeRepository.count();
        int start = rows>0 ? filter.getFirst()/rows: 1;
        List<FieldOfKnowledge> entitys = fieldOfKnowledgeRepository.findWithFilter(new PageRequest(start, rows == 0 ? 10 : rows), filter.getGlobalFilter());
        List<FieldOfKnowledgeModel> result = new ArrayList<FieldOfKnowledgeModel>();
        for (FieldOfKnowledge e: entitys) {
            result.add((FieldOfKnowledgeModel) mapperService.toModel(e));
        }
        return result;
    }

    public List<FieldOfKnowledgeModel> GetAll()
    {
        List<FieldOfKnowledgeModel> modelList = new ArrayList<>();
        Iterable<FieldOfKnowledge> entityList = fieldOfKnowledgeRepository.findAll();
        for (FieldOfKnowledge item: entityList) {
            FieldOfKnowledgeModel model = (FieldOfKnowledgeModel) mapperService.toModel(item);
            modelList.add(model);
        }
        return modelList;
    }

    public FieldOfKnowledgeModel Get(long id)
    {
        FieldOfKnowledge entity = fieldOfKnowledgeRepository.findOne(id);
        FieldOfKnowledgeModel model = (FieldOfKnowledgeModel) mapperService.toModel(entity);
        return model;
    }

    public void Delete(long id)
    {
        fieldOfKnowledgeRepository.delete(id);
    }

    public FieldOfKnowledgeModel Save(FieldOfKnowledgeModel fieldOfKnowledge)
    {
        FieldOfKnowledge entity = (FieldOfKnowledge) mapperService.toEntity(fieldOfKnowledge);
        FieldOfKnowledge model = fieldOfKnowledgeRepository.save(entity);
        fieldOfKnowledge = (FieldOfKnowledgeModel) mapperService.toModel(model);
        return fieldOfKnowledge;
    }
}

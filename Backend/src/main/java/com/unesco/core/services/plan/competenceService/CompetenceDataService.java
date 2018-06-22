package com.unesco.core.services.plan.competenceService;

import com.unesco.core.entities.plan.CompetenceEntity;
import com.unesco.core.models.additional.FilterQueryDTO;
import com.unesco.core.repositories.plan.CompetenceRepository;
import com.unesco.core.services.mapperService.IMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

public class CompetenceDataService implements ICompetenceDataService {
    @Autowired
    private IMapperService mapperService;
    @Autowired
    private CompetenceRepository competenceRepository;

    public List<CompetenceEntity> GetPage(FilterQueryDTO filter) {
        int rows = filter.getRows()>0? filter.getRows() : (int) competenceRepository.count();
        int start = rows>0 ? filter.getFirst()/rows: 1;
        List<CompetenceEntity> entitys = competenceRepository.findWithFilter(new PageRequest(start, rows == 0 ? 10 : rows), filter.getGlobalFilter());
        List<CompetenceEntity> result = new ArrayList<CompetenceEntity>();
        for (CompetenceEntity e: entitys) {
            result.add((CompetenceEntity) mapperService.toModel(e));
        }
        return result;
    }

    public List<CompetenceEntity> GetAll()
    {
        List<CompetenceEntity> modelList = new ArrayList<>();
        Iterable<CompetenceEntity> entityList = competenceRepository.findAll();
        for (CompetenceEntity item: entityList) {
            CompetenceEntity model = (CompetenceEntity) mapperService.toModel(item);
            modelList.add(model);
        }
        return modelList;
    }

    public CompetenceEntity Get(long id)
    {
        CompetenceEntity entity = competenceRepository.findOne(id);
        CompetenceEntity model = (CompetenceEntity) mapperService.toModel(entity);
        return model;
    }

    public void Delete(long id)
    {
        competenceRepository.delete(id);
    }

    public CompetenceEntity Save(CompetenceEntity competenceEntity)
    {
        CompetenceEntity entity = (CompetenceEntity) mapperService.toEntity(competenceEntity);
        CompetenceEntity model = competenceRepository.save(entity);
        competenceEntity = (CompetenceEntity) mapperService.toModel(model);
        return competenceEntity;
    }
}

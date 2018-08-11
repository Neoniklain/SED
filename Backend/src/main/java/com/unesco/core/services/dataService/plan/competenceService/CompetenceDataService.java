package com.unesco.core.services.dataService.plan.competenceService;

import com.unesco.core.entities.plan.CompetenceEntity;
import com.unesco.core.dto.additional.FilterQueryDTO;
import com.unesco.core.repositories.plan.CompetenceRepository;
import com.unesco.core.services.dataService.mapperService.IMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

public class CompetenceDataService implements ICompetenceDataService {
    @Autowired
    private IMapperService mapperService;
    @Autowired
    private CompetenceRepository competenceRepository;

    public List<CompetenceEntity> getPage(FilterQueryDTO filter) {
        int rows = filter.getRows()>0? filter.getRows() : (int) competenceRepository.count();
        int start = rows>0 ? filter.getFirst()/rows: 1;
        List<CompetenceEntity> entitys = competenceRepository.findWithFilter(new PageRequest(start, rows == 0 ? 10 : rows), filter.getGlobalFilter());
        List<CompetenceEntity> result = new ArrayList<CompetenceEntity>();
        for (CompetenceEntity e: entitys) {
            result.add((CompetenceEntity) mapperService.toDto(e));
        }
        return result;
    }

    public List<CompetenceEntity> getAll()
    {
        List<CompetenceEntity> modelList = new ArrayList<>();
        Iterable<CompetenceEntity> entityList = competenceRepository.findAll();
        for (CompetenceEntity item: entityList) {
            CompetenceEntity model = (CompetenceEntity) mapperService.toDto(item);
            modelList.add(model);
        }
        return modelList;
    }

    public CompetenceEntity get(long id)
    {
        CompetenceEntity entity = competenceRepository.findOne(id);
        CompetenceEntity model = (CompetenceEntity) mapperService.toDto(entity);
        return model;
    }

    public void delete(long id)
    {
        competenceRepository.delete(id);
    }

    public CompetenceEntity save(CompetenceEntity competenceEntity)
    {
        CompetenceEntity entity = (CompetenceEntity) mapperService.toEntity(competenceEntity);
        CompetenceEntity model = competenceRepository.save(entity);
        competenceEntity = (CompetenceEntity) mapperService.toDto(model);
        return competenceEntity;
    }
}

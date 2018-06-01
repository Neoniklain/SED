package com.unesco.core.services.plan.competenceService;

import com.unesco.core.entities.plan.Competence;
import com.unesco.core.models.additional.FilterQuery;
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

    public List<Competence> GetPage(FilterQuery filter) {
        int rows = filter.getRows()>0? filter.getRows() : (int) competenceRepository.count();
        int start = rows>0 ? filter.getFirst()/rows: 1;
        List<Competence> entitys = competenceRepository.findWithFilter(new PageRequest(start, rows == 0 ? 10 : rows), filter.getGlobalFilter());
        List<Competence> result = new ArrayList<Competence>();
        for (Competence e: entitys) {
            result.add((Competence) mapperService.toModel(e));
        }
        return result;
    }

    public List<Competence> GetAll()
    {
        List<Competence> modelList = new ArrayList<>();
        Iterable<Competence> entityList = competenceRepository.findAll();
        for (Competence item: entityList) {
            Competence model = (Competence) mapperService.toModel(item);
            modelList.add(model);
        }
        return modelList;
    }

    public Competence Get(long id)
    {
        Competence entity = competenceRepository.findOne(id);
        Competence model = (Competence) mapperService.toModel(entity);
        return model;
    }

    public void Delete(long id)
    {
        competenceRepository.delete(id);
    }

    public Competence Save(Competence competence)
    {
        Competence entity = (Competence) mapperService.toEntity(competence);
        Competence model = competenceRepository.save(entity);
        competence = (Competence) mapperService.toModel(model);
        return competence;
    }
}

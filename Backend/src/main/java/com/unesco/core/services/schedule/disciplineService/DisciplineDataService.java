package com.unesco.core.services.schedule.disciplineService;

import com.unesco.core.entities.schedule.Discipline;
import com.unesco.core.models.shedule.DisciplineModel;
import com.unesco.core.models.additional.FilterQuery;
import com.unesco.core.repositories.plan.DisciplineRepository;
import com.unesco.core.services.mapperService.IMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DisciplineDataService implements IDisciplineDataService {

    @Autowired
    private IMapperService mapperService;
    @Autowired
    private DisciplineRepository disciplineRepository;

    public List<DisciplineModel> GetPage(FilterQuery filter) {
        int rows = filter.getRows()>0? filter.getRows() : (int) disciplineRepository.count();
        int start = rows>0 ? filter.getFirst()/rows: 1;
        List<Discipline> entitys = disciplineRepository.findWithFilter(new PageRequest(start, rows == 0 ? 10 : rows), filter.getGlobalFilter());
        List<DisciplineModel> result = new ArrayList<DisciplineModel>();
        for (Discipline e: entitys) {
            result.add((DisciplineModel) mapperService.toModel(e));
        }
        return result;
    }

    public List<DisciplineModel> GetAll()
    {
        List<DisciplineModel> modelList = new ArrayList<>();
        Iterable<Discipline> entityList = disciplineRepository.findAll();
        for (Discipline item: entityList) {
            DisciplineModel model = (DisciplineModel) mapperService.toModel(item);
            modelList.add(model);
        }
        return modelList;
    }

    public DisciplineModel Get(long id)
    {
        Discipline entity = disciplineRepository.findOne(id);
        DisciplineModel model = (DisciplineModel) mapperService.toModel(entity);
        return model;
    }

    public void Delete(long id)
    {
        disciplineRepository.delete(id);
    }

    public DisciplineModel Save(DisciplineModel discipline)
    {
        Discipline entity = (Discipline) mapperService.toEntity(discipline);
        Discipline model = disciplineRepository.save(entity);
        discipline = (DisciplineModel) mapperService.toModel(model);
        return discipline;
    }

}

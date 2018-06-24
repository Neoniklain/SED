package com.unesco.core.services.schedule.disciplineService;

import com.unesco.core.entities.schedule.DisciplineEntity;
import com.unesco.core.models.additional.FilterQueryDTO;
import com.unesco.core.models.shedule.DisciplineDTO;
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

    public List<DisciplineDTO> GetPage(FilterQueryDTO filter) {
        int rows = filter.getRows()>0? filter.getRows() : (int) disciplineRepository.count();
        int start = rows>0 ? filter.getFirst()/rows: 1;
        List<DisciplineEntity> entitys = disciplineRepository.findWithFilter(new PageRequest(start, rows == 0 ? 10 : rows), filter.getGlobalFilter());
        List<DisciplineDTO> result = new ArrayList<DisciplineDTO>();
        for (DisciplineEntity e: entitys) {
            result.add((DisciplineDTO) mapperService.toModel(e));
        }
        return result;
    }

    public List<DisciplineDTO> GetAll()
    {
        List<DisciplineDTO> modelList = new ArrayList<>();
        Iterable<DisciplineEntity> entityList = disciplineRepository.findAll();
        for (DisciplineEntity item: entityList) {
            DisciplineDTO model = (DisciplineDTO) mapperService.toModel(item);
            modelList.add(model);
        }
        return modelList;
    }

    public DisciplineDTO Get(long id)
    {
        DisciplineEntity entity = disciplineRepository.findOne(id);
        DisciplineDTO model = (DisciplineDTO) mapperService.toModel(entity);
        return model;
    }

    public DisciplineDTO GetByName(String name)
    {
        DisciplineEntity entity = disciplineRepository.findByName(name);
        DisciplineDTO model = (DisciplineDTO) mapperService.toModel(entity);
        return model;
    }

    public void Delete(long id)
    {
        disciplineRepository.delete(id);
    }

    public DisciplineDTO Save(DisciplineDTO discipline)
    {
        DisciplineEntity entity = (DisciplineEntity) mapperService.toEntity(discipline);
        DisciplineEntity model = disciplineRepository.save(entity);
        discipline = (DisciplineDTO) mapperService.toModel(model);
        return discipline;
    }

}

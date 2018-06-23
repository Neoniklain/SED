package com.unesco.core.services.schedule.instituteService;

import com.unesco.core.entities.schedule.InstituteEntity;
import com.unesco.core.models.additional.FilterQueryDTO;
import com.unesco.core.models.shedule.InstituteDTO;
import com.unesco.core.repositories.plan.InstituteRepository;
import com.unesco.core.services.mapperService.IMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InstituteDataService implements IInstituteDataService {

    @Autowired
    private IMapperService mapperService;
    @Autowired
    private InstituteRepository instituteRepository;

    public List<InstituteDTO> GetPage(FilterQueryDTO filter) {
        int rows = filter.getRows()>0? filter.getRows() : (int) instituteRepository.count();
        int start = rows>0 ? filter.getFirst()/rows: 1;
        List<InstituteEntity> entitys = instituteRepository.findWithFilter(new PageRequest(start, rows == 0 ? 10 : rows), filter.getGlobalFilter());
        List<InstituteDTO> result = new ArrayList<InstituteDTO>();
        for (InstituteEntity e: entitys) {
            result.add((InstituteDTO) mapperService.toModel(e));
        }
        return result;
    }

    public List<InstituteDTO> GetAll()
    {
        List<InstituteDTO> modelList = new ArrayList<>();
        Iterable<InstituteEntity> entityList = instituteRepository.findAll();
        for (InstituteEntity item: entityList) {
            InstituteDTO model = (InstituteDTO) mapperService.toModel(item);
            modelList.add(model);
        }
        return modelList;
    }

    public InstituteDTO Get(long id)
    {
        InstituteEntity entity = instituteRepository.findOne(id);
        InstituteDTO model = (InstituteDTO) mapperService.toModel(entity);
        return model;
    }

    public InstituteDTO GetByName(String name)
    {
        InstituteEntity entity = instituteRepository.findByName(name);
        InstituteDTO model = (InstituteDTO) mapperService.toModel(entity);
        return model;
    }

    public void Delete(long id)
    {
        instituteRepository.delete(id);
    }

    public InstituteDTO Save(InstituteDTO institute)
    {
        InstituteEntity entity = (InstituteEntity) mapperService.toEntity(institute);
        InstituteEntity model = instituteRepository.save(entity);
        institute = (InstituteDTO) mapperService.toModel(model);
        return institute;
    }
}

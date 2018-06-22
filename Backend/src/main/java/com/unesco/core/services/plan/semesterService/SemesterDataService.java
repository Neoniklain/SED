package com.unesco.core.services.plan.semesterService;

import com.unesco.core.entities.plan.SemesterEntity;
import com.unesco.core.models.plan.SemesterDTO;
import com.unesco.core.models.additional.FilterQueryDTO;
import com.unesco.core.repositories.plan.SemesterRepository;
import com.unesco.core.services.mapperService.IMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SemesterDataService implements ISemesterDataService {

    @Autowired
    private IMapperService mapperService;
    @Autowired
    private SemesterRepository semesterRepository;

    public List<SemesterDTO> GetPage(FilterQueryDTO filter) {
        int rows = filter.getRows()>0? filter.getRows() : (int) semesterRepository.count();
        int start = rows>0 ? filter.getFirst()/rows: 1;
        List<SemesterEntity> entitys = semesterRepository.findWithFilter(new PageRequest(start, rows == 0 ? 10 : rows), filter.getGlobalFilter());
        List<SemesterDTO> result = new ArrayList<SemesterDTO>();
        for (SemesterEntity e: entitys) {
            result.add((SemesterDTO) mapperService.toModel(e));
        }
        return result;
    }

    public List<SemesterDTO> GetAll()
    {
        List<SemesterDTO> modelList = new ArrayList<>();
        Iterable<SemesterEntity> entityList = semesterRepository.findAll();
        for (SemesterEntity item: entityList) {
            SemesterDTO model = (SemesterDTO) mapperService.toModel(item);
            modelList.add(model);
        }
        return modelList;
    }

    public SemesterDTO Get(long id)
    {
        SemesterEntity entity = semesterRepository.findOne(id);
        SemesterDTO model = (SemesterDTO) mapperService.toModel(entity);
        return model;
    }

    public void Delete(long id)
    {
        semesterRepository.delete(id);
    }

    public SemesterDTO Save(SemesterDTO semester)
    {
        SemesterEntity entity = (SemesterEntity) mapperService.toEntity(semester);
        SemesterEntity model = semesterRepository.save(entity);
        semester = (SemesterDTO) mapperService.toModel(model);
        return semester;
    }
}

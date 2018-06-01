package com.unesco.core.services.plan.semesterService;

import com.unesco.core.entities.plan.Semester;
import com.unesco.core.models.plan.SemesterModel;
import com.unesco.core.models.additional.FilterQuery;
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

    public List<SemesterModel> GetPage(FilterQuery filter) {
        int rows = filter.getRows()>0? filter.getRows() : (int) semesterRepository.count();
        int start = rows>0 ? filter.getFirst()/rows: 1;
        List<Semester> entitys = semesterRepository.findWithFilter(new PageRequest(start, rows == 0 ? 10 : rows), filter.getGlobalFilter());
        List<SemesterModel> result = new ArrayList<SemesterModel>();
        for (Semester e: entitys) {
            result.add((SemesterModel) mapperService.toModel(e));
        }
        return result;
    }

    public List<SemesterModel> GetAll()
    {
        List<SemesterModel> modelList = new ArrayList<>();
        Iterable<Semester> entityList = semesterRepository.findAll();
        for (Semester item: entityList) {
            SemesterModel model = (SemesterModel) mapperService.toModel(item);
            modelList.add(model);
        }
        return modelList;
    }

    public SemesterModel Get(long id)
    {
        Semester entity = semesterRepository.findOne(id);
        SemesterModel model = (SemesterModel) mapperService.toModel(entity);
        return model;
    }

    public void Delete(long id)
    {
        semesterRepository.delete(id);
    }

    public SemesterModel Save(SemesterModel semester)
    {
        Semester entity = (Semester) mapperService.toEntity(semester);
        Semester model = semesterRepository.save(entity);
        semester = (SemesterModel) mapperService.toModel(model);
        return semester;
    }
}

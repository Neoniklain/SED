package com.unesco.core.services.schedule.instituteService;

import com.unesco.core.entities.schedule.Institute;
import com.unesco.core.models.shedule.InstituteModel;
import com.unesco.core.models.additional.FilterQuery;
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

    public List<InstituteModel> GetPage(FilterQuery filter) {
        int rows = filter.getRows()>0? filter.getRows() : (int) instituteRepository.count();
        int start = rows>0 ? filter.getFirst()/rows: 1;
        List<Institute> entitys = instituteRepository.findWithFilter(new PageRequest(start, rows == 0 ? 10 : rows), filter.getGlobalFilter());
        List<InstituteModel> result = new ArrayList<InstituteModel>();
        for (Institute e: entitys) {
            result.add((InstituteModel) mapperService.toModel(e));
        }
        return result;
    }

    public List<InstituteModel> GetAll()
    {
        List<InstituteModel> modelList = new ArrayList<>();
        Iterable<Institute> entityList = instituteRepository.findAll();
        for (Institute item: entityList) {
            InstituteModel model = (InstituteModel) mapperService.toModel(item);
            modelList.add(model);
        }
        return modelList;
    }

    public InstituteModel Get(long id)
    {
        Institute entity = instituteRepository.findOne(id);
        InstituteModel model = (InstituteModel) mapperService.toModel(entity);
        return model;
    }

    public void Delete(long id)
    {
        instituteRepository.delete(id);
    }

    public InstituteModel Save(InstituteModel institute)
    {
        Institute entity = (Institute) mapperService.toEntity(institute);
        Institute model = instituteRepository.save(entity);
        institute = (InstituteModel) mapperService.toModel(model);
        return institute;
    }
}

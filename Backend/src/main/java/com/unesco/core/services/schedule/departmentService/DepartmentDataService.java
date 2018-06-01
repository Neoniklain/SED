package com.unesco.core.services.schedule.departmentService;

import com.unesco.core.entities.schedule.Department;
import com.unesco.core.models.plan.DepartmentModel;
import com.unesco.core.models.additional.FilterQuery;
import com.unesco.core.repositories.plan.DepartmentRepository;
import com.unesco.core.services.mapperService.IMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentDataService implements IDepartmentDataService {

    @Autowired
    private IMapperService mapperService;
    @Autowired
    private DepartmentRepository departmentRepository;

    public List<DepartmentModel> GetPage(FilterQuery filter) {
        int rows = filter.getRows()>0? filter.getRows() : (int) departmentRepository.count();
        int start = rows>0 ? filter.getFirst()/rows: 1;
        List<Department> entitys = departmentRepository.findWithFilter(new PageRequest(start, rows == 0 ? 10 : rows), filter.getGlobalFilter());
        List<DepartmentModel> result = new ArrayList<DepartmentModel>();
        for (Department e: entitys) {
            result.add((DepartmentModel) mapperService.toModel(e));
        }
        return result;
    }

    public List<DepartmentModel> GetAll()
    {
        List<DepartmentModel> modelList = new ArrayList<>();
        Iterable<Department> entityList = departmentRepository.findAll();
        for (Department item: entityList) {
            DepartmentModel model = (DepartmentModel) mapperService.toModel(item);
            modelList.add(model);
        }
        return modelList;
    }

    public DepartmentModel Get(long id)
    {
        Department entity = departmentRepository.findOne(id);
        DepartmentModel model = (DepartmentModel) mapperService.toModel(entity);
        return model;
    }

    public void Delete(long id)
    {
        departmentRepository.delete(id);
    }

    public DepartmentModel Save(DepartmentModel department)
    {
        Department entity = (Department) mapperService.toEntity(department);
        Department model = departmentRepository.save(entity);
        department = (DepartmentModel) mapperService.toModel(model);
        return department;
    }
}

package com.unesco.core.services.schedule.departmentService;

import com.unesco.core.entities.schedule.DepartmentEntity;
import com.unesco.core.models.plan.DepartmentDTO;
import com.unesco.core.models.additional.FilterQueryDTO;
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

    public List<DepartmentDTO> GetPage(FilterQueryDTO filter) {
        int rows = filter.getRows()>0? filter.getRows() : (int) departmentRepository.count();
        int start = rows>0 ? filter.getFirst()/rows: 1;
        List<DepartmentEntity> entitys = departmentRepository.findWithFilter(new PageRequest(start, rows == 0 ? 10 : rows), filter.getGlobalFilter());
        List<DepartmentDTO> result = new ArrayList<DepartmentDTO>();
        for (DepartmentEntity e: entitys) {
            result.add((DepartmentDTO) mapperService.toModel(e));
        }
        return result;
    }

    public List<DepartmentDTO> GetAll()
    {
        List<DepartmentDTO> modelList = new ArrayList<>();
        Iterable<DepartmentEntity> entityList = departmentRepository.findAll();
        for (DepartmentEntity item: entityList) {
            DepartmentDTO model = (DepartmentDTO) mapperService.toModel(item);
            modelList.add(model);
        }
        return modelList;
    }

    public DepartmentDTO Get(long id)
    {
        DepartmentEntity entity = departmentRepository.findOne(id);
        DepartmentDTO model = (DepartmentDTO) mapperService.toModel(entity);
        return model;
    }

    public DepartmentDTO GetByName(String name)
    {
        DepartmentEntity entity = departmentRepository.findByName(name);
        DepartmentDTO model = (DepartmentDTO) mapperService.toModel(entity);
        return model;
    }

    public void Delete(long id)
    {
        departmentRepository.delete(id);
    }

    public DepartmentDTO Save(DepartmentDTO department)
    {
        DepartmentEntity entity = (DepartmentEntity) mapperService.toEntity(department);
        DepartmentEntity model = departmentRepository.save(entity);
        department = (DepartmentDTO) mapperService.toModel(model);
        return department;
    }
}

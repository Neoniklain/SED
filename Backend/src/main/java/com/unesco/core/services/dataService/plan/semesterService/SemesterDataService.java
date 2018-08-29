package com.unesco.core.services.dataService.plan.semesterService;

import com.unesco.core.dto.additional.FilterQueryDTO;
import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.enums.StatusTypes;
import com.unesco.core.dto.plan.SemesterDTO;
import com.unesco.core.entities.plan.SemesterEntity;
import com.unesco.core.repositories.plan.SemesterRepository;
import com.unesco.core.services.mapperService.IMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

    public List<SemesterDTO> getPage(FilterQueryDTO filter) {
        int rows = filter.getRows()>0? filter.getRows() : (int) semesterRepository.count();
        int start = rows>0 ? filter.getFirst()/rows: 1;
        List<SemesterEntity> entitys = semesterRepository.findWithFilter(new PageRequest(start, rows == 0 ? 10 : rows), filter.getGlobalFilter());
        List<SemesterDTO> result = new ArrayList<SemesterDTO>();
        for (SemesterEntity e: entitys) {
            result.add((SemesterDTO) mapperService.toDto(e));
        }
        return result;
    }

    public List<SemesterDTO> getAll()
    {
        List<SemesterDTO> modelList = new ArrayList<>();
        Iterable<SemesterEntity> entityList = semesterRepository.findAll();
        for (SemesterEntity item: entityList) {
            SemesterDTO model = (SemesterDTO) mapperService.toDto(item);
            modelList.add(model);
        }
        return modelList;
    }

    public SemesterDTO get(long id)
    {
        SemesterEntity entity = semesterRepository.findOne(id);
        SemesterDTO model = (SemesterDTO) mapperService.toDto(entity);
        return model;
    }

    public ResponseStatusDTO<SemesterDTO> delete(long id)
    {
        ResponseStatusDTO<SemesterDTO> result = new ResponseStatusDTO<>(StatusTypes.OK);
        try {
            semesterRepository.delete(id);
        } catch (Exception e) {
            result.setStatus(StatusTypes.ERROR);
            if(e instanceof DataIntegrityViolationException)
                result.addErrors("Удаление не удалось. У объекта есть зависимости.");
            result.addErrors("Удаление не удалось");
            return result;
        }
        return result;
    }

    public ResponseStatusDTO<SemesterDTO> save(SemesterDTO semester)
    {
        SemesterEntity entity = (SemesterEntity) mapperService.toEntity(semester);
        ResponseStatusDTO<SemesterDTO> result = new ResponseStatusDTO<>(StatusTypes.OK);
        try {
            entity = semesterRepository.save(entity);
        } catch (Exception e) {
            result.setStatus(StatusTypes.ERROR);
            result.addErrors(e.getMessage());
            return result;
        }
        result.setData((SemesterDTO) mapperService.toDto(entity));
        return result;
    }
}

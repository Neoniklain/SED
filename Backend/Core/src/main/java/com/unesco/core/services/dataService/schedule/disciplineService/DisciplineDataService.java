package com.unesco.core.services.dataService.schedule.disciplineService;

import com.unesco.core.dto.additional.FilterQueryDTO;
import com.unesco.core.dto.additional.PageResultDTO;
import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.enums.StatusTypes;
import com.unesco.core.dto.shedule.DisciplineDTO;
import com.unesco.core.entities.schedule.DisciplineEntity;
import com.unesco.core.repositories.plan.DisciplineRepository;
import com.unesco.core.services.mapperService.IMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

    public PageResultDTO<DisciplineDTO> getPage(FilterQueryDTO filter) {
        int rows = filter.getRows()>0? filter.getRows() : (int) disciplineRepository.count();
        int start = rows>0 ? filter.getFirst()/rows: 1;
        List<DisciplineEntity> entitys = disciplineRepository.findWithFilter(new PageRequest(start, rows == 0 ? 10 : rows), filter.getGlobalFilter());
        List<DisciplineDTO> result = new ArrayList<DisciplineDTO>();
        for (DisciplineEntity e: entitys) {
            result.add((DisciplineDTO) mapperService.toDto(e));
        }
        return new PageResultDTO(result, disciplineRepository.count());
    }

    public List<DisciplineDTO> getAll()
    {
        List<DisciplineDTO> modelList = new ArrayList<>();
        Iterable<DisciplineEntity> entityList = disciplineRepository.findAll();
        for (DisciplineEntity item: entityList) {
            DisciplineDTO model = (DisciplineDTO) mapperService.toDto(item);
            modelList.add(model);
        }
        return modelList;
    }

    public DisciplineDTO get(long id)
    {
        DisciplineEntity entity = disciplineRepository.findOne(id);
        DisciplineDTO model = (DisciplineDTO) mapperService.toDto(entity);
        return model;
    }

    public DisciplineDTO getByName(String name)
    {
        DisciplineEntity entity = disciplineRepository.findByName(name);
        DisciplineDTO model = (DisciplineDTO) mapperService.toDto(entity);
        return model;
    }

    public ResponseStatusDTO<DisciplineDTO> delete(long id)
    {
        ResponseStatusDTO<DisciplineDTO> result = new ResponseStatusDTO<>(StatusTypes.OK);
        try {
            disciplineRepository.delete(id);
        } catch (Exception e) {
            result.setStatus(StatusTypes.ERROR);
            if(e instanceof DataIntegrityViolationException)
                result.addErrors("Удаление не удалось. У объекта есть зависимости.");
            result.addErrors("Удаление не удалось");
            return result;
        }
        return result;
    }

    public ResponseStatusDTO<DisciplineDTO> save(DisciplineDTO discipline)
    {
        DisciplineEntity entity = (DisciplineEntity) mapperService.toEntity(discipline);
        ResponseStatusDTO<DisciplineDTO> result = new ResponseStatusDTO<>(StatusTypes.OK);
        DisciplineEntity model;
        try {
            model = disciplineRepository.save(entity);
        } catch (Exception e) {
            result.setStatus(StatusTypes.ERROR);
            if(e instanceof DataIntegrityViolationException)
                result.addErrors("Данная дисциплина уже существует.");
            else
                result.addErrors("Ошибка добавления.");
            return result;
        }
        result.setData((DisciplineDTO) mapperService.toDto(model));
        return result;
    }

}

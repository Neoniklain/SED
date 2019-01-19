package com.unesco.core.services.dataService.schedule.specialityService;

import com.unesco.core.dto.additional.FilterQueryDTO;
import com.unesco.core.dto.additional.PageResultDTO;
import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.enums.StatusTypes;
import com.unesco.core.dto.shedule.SpecialityDTO;
import com.unesco.core.entities.schedule.SpecialityEntity;
import com.unesco.core.repositories.schedule.SpecialityRepository;
import com.unesco.core.services.mapperService.IMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpecialityDataService implements ISpecialityDataService {
    @Autowired
    private IMapperService mapperService;
    @Autowired
    private SpecialityRepository specialityRepository;

    public PageResultDTO<SpecialityDTO> getPage(FilterQueryDTO filter) {
        int rows = filter.getRows()>0? filter.getRows() : (int) specialityRepository.count();
        int start = rows>0 ? filter.getFirst()/rows: 1;
        List<SpecialityEntity> entitys = specialityRepository.findWithFilter(new PageRequest(start, rows == 0 ? 10 : rows), filter.getGlobalFilter());
        List<SpecialityDTO> result = new ArrayList<SpecialityDTO>();
        for (SpecialityEntity e: entitys) {
            result.add((SpecialityDTO) mapperService.toDto(e));
        }
        return new PageResultDTO(result, specialityRepository.count());
    }

    public List<SpecialityDTO> getAll()
    {
        List<SpecialityDTO> modelList = new ArrayList<>();
        Iterable<SpecialityEntity> entityList = specialityRepository.findAll();
        for (SpecialityEntity item: entityList) {
            SpecialityDTO model = (SpecialityDTO) mapperService.toDto(item);
            modelList.add(model);
        }
        return modelList;
    }

    public SpecialityDTO get(long id)
    {
        SpecialityEntity entity = specialityRepository.findOne(id);
        SpecialityDTO model = (SpecialityDTO) mapperService.toDto(entity);
        return model;
    }

    public SpecialityDTO getBySpeciality(String speciality)
    {
        SpecialityEntity entity = specialityRepository.findByName(speciality);
        SpecialityDTO model = (SpecialityDTO) mapperService.toDto(entity);
        return model;
    }

    public ResponseStatusDTO<SpecialityDTO> delete(long id)
    {
        ResponseStatusDTO<SpecialityDTO> result = new ResponseStatusDTO<>(StatusTypes.OK);
        try {
            specialityRepository.delete(id);
        } catch (DataAccessException e) {
            result.setStatus(StatusTypes.ERROR);
            if(e instanceof DataIntegrityViolationException)
                result.addErrors("Удаление не удалось. У объекта есть зависимости.");
            result.addErrors("Удаление не удалось");
            return result;
        }
        return result;
    }

    public ResponseStatusDTO<SpecialityDTO> save(SpecialityDTO speciality)
    {
        SpecialityEntity entity = (SpecialityEntity) mapperService.toEntity(speciality);
        ResponseStatusDTO<SpecialityDTO> result = new ResponseStatusDTO<>(StatusTypes.OK);
        try {
            entity = specialityRepository.save(entity);
        } catch (Exception e) {
            if(e instanceof DataIntegrityViolationException)
                result.addErrors("Данная аудитория уже существует.");
            else
                result.addErrors("Ошибка добавления.");
            result.setStatus(StatusTypes.ERROR);
            return result;
        }
        result.setData((SpecialityDTO) mapperService.toDto(entity));
        return result;
    }
}

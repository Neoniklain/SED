package com.unesco.core.services.dataService.schedule.pairTypeService;

import com.unesco.core.dto.additional.FilterQueryDTO;
import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.enums.StatusTypes;
import com.unesco.core.dto.shedule.PairTypeDTO;
import com.unesco.core.entities.schedule.PairTypeEntity;
import com.unesco.core.repositories.PairTypeRepository;
import com.unesco.core.services.mapperService.IMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PairTypeDataService implements IPairTypeDataService {

    @Autowired
    private IMapperService mapperService;
    @Autowired
    private PairTypeRepository pairTypeRepository;

    public List<PairTypeDTO> getPage(FilterQueryDTO filter) {
        int rows = filter.getRows()>0? filter.getRows() : (int) pairTypeRepository.count();
        int start = rows>0 ? filter.getFirst()/rows: 1;
        List<PairTypeEntity> entitys = pairTypeRepository.findWithFilter(new PageRequest(start, rows == 0 ? 10 : rows), filter.getGlobalFilter());
        List<PairTypeDTO> result = new ArrayList<PairTypeDTO>();
        for (PairTypeEntity e: entitys) {
            result.add((PairTypeDTO) mapperService.toDto(e));
        }
        return result;
    }

    public List<PairTypeDTO> getAll()
    {
        List<PairTypeDTO> modelList = new ArrayList<>();
        Iterable<PairTypeEntity> entityList = pairTypeRepository.findAll();
        for (PairTypeEntity item: entityList) {
            PairTypeDTO model = (PairTypeDTO) mapperService.toDto(item);
            modelList.add(model);
        }
        return modelList;
    }

    public PairTypeDTO get(long id)
    {
        PairTypeEntity entity = pairTypeRepository.findOne(id);
        PairTypeDTO model = (PairTypeDTO) mapperService.toDto(entity);
        return model;
    }

    public PairTypeDTO getByType(String name)
    {
        PairTypeEntity entity = pairTypeRepository.findByType(name);
        PairTypeDTO model = (PairTypeDTO) mapperService.toDto(entity);
        return model;
    }

    public ResponseStatusDTO<PairTypeDTO> delete(long id)
    {
        ResponseStatusDTO<PairTypeDTO> result = new ResponseStatusDTO<>(StatusTypes.OK);
        try {
            pairTypeRepository.delete(id);
        } catch (Exception e) {
            result.setStatus(StatusTypes.ERROR);
            if(e instanceof DataIntegrityViolationException)
                result.addErrors("Удаление не удалось. У объекта есть зависимости.");
            result.addErrors("Удаление не удалось");
            return result;
        }
        return result;
    }

    public ResponseStatusDTO<PairTypeDTO> save(PairTypeDTO pairType)
    {
        PairTypeEntity entity = (PairTypeEntity) mapperService.toEntity(pairType);
        ResponseStatusDTO<PairTypeDTO> result = new ResponseStatusDTO<>(StatusTypes.OK);
        try {
            entity = pairTypeRepository.save(entity);
        } catch (Exception e) {
            result.setStatus(StatusTypes.ERROR);
            if(e instanceof DataIntegrityViolationException)
                result.addErrors("Данный институт уже существует.");
            else
                result.addErrors("Ошибка добавления.");
            return result;
        }
        result.setData((PairTypeDTO) mapperService.toDto(entity));
        return result;
    }

}

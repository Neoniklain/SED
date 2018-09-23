package com.unesco.core.services.dataService.journal.pointType;

import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.enums.StatusTypes;
import com.unesco.core.dto.journal.PointTypeDTO;
import com.unesco.core.entities.journal.PointTypeEntity;
import com.unesco.core.repositories.journal.PointTypeRepository;
import com.unesco.core.services.mapperService.IMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PointTypeDataService implements IPointTypeDataService {
    @Autowired
    private IMapperService mapperService;
    @Autowired
    private PointTypeRepository pointTypeRepository;

    public List<PointTypeDTO> getAll()
    {
        List<PointTypeDTO> modelList = new ArrayList<>();
        Iterable<PointTypeEntity> entityList = pointTypeRepository.findAll();
        for (PointTypeEntity item: entityList) {
            PointTypeDTO model = (PointTypeDTO) mapperService.toDto(item);
            modelList.add(model);
        }
        return modelList;
    }

    public PointTypeDTO get(long id)
    {
        PointTypeEntity entity = pointTypeRepository.findOne(id);
        PointTypeDTO model = (PointTypeDTO) mapperService.toDto(entity);
        return model;
    }

    public ResponseStatusDTO<PointTypeDTO> delete(long id)
    {
        ResponseStatusDTO<PointTypeDTO> result = new ResponseStatusDTO<>(StatusTypes.OK);
        try {
            pointTypeRepository.delete(id);
        } catch (Exception e) {
            result.setStatus(StatusTypes.ERROR);
            if(e instanceof DataIntegrityViolationException)
                result.addErrors("Удаление не удалось. У объекта есть зависимости.");
            result.addErrors("Удаление не удалось");
            return result;
        }
        return result;
    }

    public ResponseStatusDTO<PointTypeDTO> save(PointTypeDTO model)
    {
        PointTypeEntity entity = (PointTypeEntity) mapperService.toEntity(model);
        ResponseStatusDTO<PointTypeDTO> result = new ResponseStatusDTO<>(StatusTypes.OK);
        try {
            entity = pointTypeRepository.save(entity);
        } catch (Exception e) {
            result.setStatus(StatusTypes.ERROR);
            result.addErrors(e.getMessage());
            return result;
        }
        result.setData((PointTypeDTO) mapperService.toDto(entity));
        return result;
    }

    public PointTypeDTO findByName(String name)
    {
        PointTypeEntity entity = pointTypeRepository.findByName(name);
        PointTypeDTO model = (PointTypeDTO) mapperService.toDto(entity);
        return model;
    }

}

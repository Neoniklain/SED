package com.unesco.core.services.journal.pointType;

import com.unesco.core.entities.journal.PointTypeEntity;
import com.unesco.core.models.journal.PointTypeDTO;
import com.unesco.core.repositories.journal.PointTypeRepository;
import com.unesco.core.services.mapperService.IMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PointTypeDataService implements IPointTypeDataService {
    @Autowired
    private IMapperService mapperService;
    @Autowired
    private PointTypeRepository pointTypeRepository;

    public List<PointTypeDTO> GetAll()
    {
        List<PointTypeDTO> modelList = new ArrayList<>();
        Iterable<PointTypeEntity> entityList = pointTypeRepository.findAll();
        for (PointTypeEntity item: entityList) {
            PointTypeDTO model = (PointTypeDTO) mapperService.toModel(item);
            modelList.add(model);
        }
        return modelList;
    }

    public PointTypeDTO Get(long id)
    {
        PointTypeEntity entity = pointTypeRepository.findOne(id);
        PointTypeDTO model = (PointTypeDTO) mapperService.toModel(entity);
        return model;
    }

    public void Delete(long id)
    {
        pointTypeRepository.delete(id);
    }

    public PointTypeDTO Save(PointTypeDTO pointType)
    {
        PointTypeEntity entity = (PointTypeEntity) mapperService.toEntity(pointType);
        entity = pointTypeRepository.save(entity);
        pointType = (PointTypeDTO) mapperService.toModel(entity);
        return pointType;
    }

    public PointTypeDTO FindByName(String name)
    {
        PointTypeEntity entity = pointTypeRepository.findByName(name);
        PointTypeDTO pointType = (PointTypeDTO) mapperService.toModel(entity);
        return pointType;
    }

}

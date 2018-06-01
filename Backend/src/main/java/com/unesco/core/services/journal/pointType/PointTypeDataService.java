package com.unesco.core.services.journal.pointType;

import com.unesco.core.entities.journal.PointType;
import com.unesco.core.models.journal.PointTypeModel;
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

    public List<PointTypeModel> GetAll()
    {
        List<PointTypeModel> modelList = new ArrayList<>();
        Iterable<PointType> entityList = pointTypeRepository.findAll();
        for (PointType item: entityList) {
            PointTypeModel model = (PointTypeModel) mapperService.toModel(item);
            modelList.add(model);
        }
        return modelList;
    }

    public PointTypeModel Get(long id)
    {
        PointType entity = pointTypeRepository.findOne(id);
        PointTypeModel model = (PointTypeModel) mapperService.toModel(entity);
        return model;
    }

    public void Delete(long id)
    {
        pointTypeRepository.delete(id);
    }

    public PointTypeModel Save(PointTypeModel pointType)
    {
        PointType entity = (PointType) mapperService.toEntity(pointType);
        entity = pointTypeRepository.save(entity);
        pointType = (PointTypeModel) mapperService.toModel(entity);
        return pointType;
    }

    public PointTypeModel FindByName(String name)
    {
        PointType entity = pointTypeRepository.findByName(name);
        PointTypeModel pointType = (PointTypeModel) mapperService.toModel(entity);
        return pointType;
    }

}

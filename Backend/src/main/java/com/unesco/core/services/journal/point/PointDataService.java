package com.unesco.core.services.journal.point;

import com.unesco.core.entities.journal.PointEntity;
import com.unesco.core.dto.journal.PointDTO;
import com.unesco.core.repositories.journal.PointRepository;
import com.unesco.core.services.mapperService.IMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PointDataService implements IPointDataService {
    
    @Autowired
    private IMapperService mapperService;
    @Autowired
    private PointRepository pointRepository;

    public List<PointDTO> GetAll()
    {
        List<PointDTO> modelList = new ArrayList<>();
        Iterable<PointEntity> entityList = pointRepository.findAll();
        for (PointEntity item: entityList) {
            PointDTO model = (PointDTO) mapperService.toDto(item);
            modelList.add(model);
        }
        return modelList;
    }

    public PointDTO Get(long id)
    {
        PointEntity entity = pointRepository.findOne(id);
        PointDTO model = (PointDTO) mapperService.toDto(entity);
        return model;
    }

    public List<PointDTO> GetByStudentAndPair(long studentId, long pairId)
    {
        List<PointDTO> modelList = new ArrayList<>();
        Iterable<PointEntity> entityList = pointRepository.findByStudentIdAndPairId(studentId, pairId);
        for (PointEntity item: entityList) {
            PointDTO model = (PointDTO) mapperService.toDto(item);
            modelList.add(model);
        }
        return modelList;
    }

    public PointDTO GetByStudentAndDateAndTypeAndPair(long studentId, Date date, long typeId, long pairId)
    {
        PointEntity entity = pointRepository.findByStudentIdAndDateAndTypeIdAndPairId(studentId, date, typeId, pairId);
        PointDTO model = (PointDTO) mapperService.toDto(entity);
        return model;
    }

    public void Delete(long id)
    {
        pointRepository.delete(id);
    }

    public PointDTO Save(PointDTO point)
    {
        PointEntity entity = (PointEntity) mapperService.toEntity(point);
        entity = pointRepository.save(entity);
        point = (PointDTO) mapperService.toDto(entity);
        return point;
    }

}

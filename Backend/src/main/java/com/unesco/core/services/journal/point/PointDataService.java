package com.unesco.core.services.journal.point;

import com.unesco.core.entities.journal.Point;
import com.unesco.core.models.journal.PointModel;
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

    public List<PointModel> GetAll()
    {
        List<PointModel> modelList = new ArrayList<>();
        Iterable<Point> entityList = pointRepository.findAll();
        for (Point item: entityList) {
            PointModel model = (PointModel) mapperService.toModel(item);
            modelList.add(model);
        }
        return modelList;
    }

    public PointModel Get(long id)
    {
        Point entity = pointRepository.findOne(id);
        PointModel model = (PointModel) mapperService.toModel(entity);
        return model;
    }

    public List<PointModel> GetByStudentAndPair(long studentId, long pairId)
    {
        List<PointModel> modelList = new ArrayList<>();
        Iterable<Point> entityList = pointRepository.findByStudentIdAndPairId(studentId, pairId);
        for (Point item: entityList) {
            PointModel model = (PointModel) mapperService.toModel(item);
            modelList.add(model);
        }
        return modelList;
    }

    public PointModel GetByStudentAndDateAndTypeAndPair(long studentId, Date date, long typeId, long pairId)
    {
        Point entity = pointRepository.findByStudentIdAndDateAndTypeIdAndPairId(studentId, date, typeId, pairId);
        PointModel model = (PointModel) mapperService.toModel(entity);
        return model;
    }

    public void Delete(long id)
    {
        pointRepository.delete(id);
    }

    public PointModel Save(PointModel point)
    {
        Point entity = (Point) mapperService.toEntity(point);
        entity = pointRepository.save(entity);
        point = (PointModel) mapperService.toModel(entity);
        return point;
    }

}

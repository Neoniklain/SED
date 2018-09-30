package com.unesco.core.services.dataService.journal.point;

import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.enums.StatusTypes;
import com.unesco.core.dto.journal.PointDTO;
import com.unesco.core.entities.journal.PointEntity;
import com.unesco.core.repositories.journal.PointRepository;
import com.unesco.core.services.mapperService.IMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

    public List<PointDTO> getAll()
    {
        List<PointDTO> modelList = new ArrayList<>();
        Iterable<PointEntity> entityList = pointRepository.findAll();
        for (PointEntity item: entityList) {
            PointDTO model = (PointDTO) mapperService.toDto(item);
            modelList.add(model);
        }
        return modelList;
    }

    public PointDTO get(long id)
    {
        PointEntity entity = pointRepository.findOne(id);
        PointDTO model = (PointDTO) mapperService.toDto(entity);
        return model;
    }

    public List<PointDTO> getByStudentAndPair(long studentId, long pairId)
    {
        List<PointDTO> modelList = new ArrayList<>();
        Iterable<PointEntity> entityList = pointRepository.findByStudentIdAndPairId(studentId, pairId);
        for (PointEntity item: entityList) {
            PointDTO model = (PointDTO) mapperService.toDto(item);
            modelList.add(model);
        }
        return modelList;
    }

    public List<PointDTO> getByStudentAndPairBetweenDate(long studentId, long pairId, Date from, Date to)
    {
        List<PointDTO> modelList = new ArrayList<>();
        Iterable<PointEntity> entityList = pointRepository.findByStudentIdAndPairIdAndMonth(studentId, pairId, from, to);
        for (PointEntity item: entityList) {
            PointDTO model = (PointDTO) mapperService.toDto(item);
            modelList.add(model);
        }
        return modelList;
    }

    public List<PointDTO> getByLesson(long lessonId)
    {
        List<PointDTO> modelList = new ArrayList<>();
        Iterable<PointEntity> entityList = pointRepository.findByLesson(lessonId);
        for (PointEntity item: entityList) {
            PointDTO model = (PointDTO) mapperService.toDto(item);
            modelList.add(model);
        }
        return modelList;
    }

    public PointDTO getByStudentAndDateAndTypeAndPair(long studentId, Date date, long typeId, long pairId)
    {
        PointEntity entity = pointRepository.findByStudentIdAndDateAndTypeIdAndPairId(studentId, date, typeId, pairId);
        PointDTO model = (PointDTO) mapperService.toDto(entity);
        return model;
    }

    public ResponseStatusDTO<PointDTO> delete(long id)
    {
        ResponseStatusDTO<PointDTO> result = new ResponseStatusDTO<>(StatusTypes.OK);
        try {
            pointRepository.delete(id);
        } catch (Exception e) {
            result.setStatus(StatusTypes.ERROR);
            if(e instanceof DataIntegrityViolationException)
                result.addErrors("Удаление не удалось. У объекта есть зависимости.");
            result.addErrors("Удаление не удалось");
            return result;
        }
        return result;
    }

    public ResponseStatusDTO<PointDTO> save(PointDTO point)
    {
        PointEntity entity = (PointEntity) mapperService.toEntity(point);
        ResponseStatusDTO<PointDTO> result = new ResponseStatusDTO<>(StatusTypes.OK);
        try {
            entity = pointRepository.save(entity);
        } catch (Exception e) {
            result.setStatus(StatusTypes.ERROR);
            result.addErrors(e.getMessage());
            return result;
        }
        result.setData((PointDTO) mapperService.toDto(entity));
        return result;
    }

}

package com.unesco.core.services.dataService.journal.point;

import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.enums.StatusTypes;
import com.unesco.core.dto.journal.PointDTO;
import com.unesco.core.entities.journal.PointEntity;
import com.unesco.core.repositories.journal.PointRepository;
import com.unesco.core.services.mapperService.IMapperService;
import com.unesco.core.utils.DateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PointDataService implements IPointDataService {

    @Autowired
    private IMapperService mapperService;
    @Autowired
    private PointRepository pointRepository;

    public List<PointDTO> getAll()
    {
        Iterable<PointEntity> entityList = pointRepository.findAll();
        return entityListToDtoList((List<PointEntity>) entityList);
    }

    public PointDTO get(long id)
    {
        PointEntity entity = pointRepository.findOne(id);
        return (PointDTO) mapperService.toDto(entity);
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
        entity.setDateOfCreate(DateHelper.getZeroTimeDate(new Date()));
        entity.setDate(DateHelper.getZeroTimeDate(point.getDate()));
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

    public List<PointDTO> getAllByLesson(long lessonId)
    {
        List<PointEntity> entityList = pointRepository.findByLesson(lessonId);
        return entityListToDtoList(entityList);
    }

    public List<PointDTO> getByLesson(long lessonId)
    {
        List<PointEntity> entityList = pointRepository.findByLesson(lessonId);
        entityList = getLastPoint(entityList);
        return entityListToDtoList(entityList);
    }

    public List<PointDTO> getByLesson(long lessonId, Date date)
    {
        List<PointEntity> entityList = pointRepository.findByLesson(lessonId);
        entityList = getPointForDate(entityList, date);
        return entityListToDtoList(entityList);
    }

    public List<PointDTO> getByStudentAndPairBetweenDate(long studentId, long pairId, Date from, Date to)
    {
        List<PointEntity> entityList = pointRepository.findByStudentIdAndPairIdAndMonth(studentId, pairId, from, to);
        entityList = getLastPoint(entityList);
        return entityListToDtoList(entityList);
    }

    public List<PointDTO> getByStudentAndPairBetweenDate(long studentId, long pairId, Date from, Date to, Date date)
    {
        List<PointEntity> entityList = pointRepository.findByStudentIdAndPairIdAndMonth(studentId, pairId, from, to);
        entityList = getPointForDate(entityList, date);
        return entityListToDtoList(entityList);
    }

    public PointDTO getEqualPoint(long studentId, Date date, long typeId, long pairId, Date dateOfCreate)
    {
        PointEntity  entity = pointRepository.findByStudentIdAndDateAndTypeIdAndPairIdAndDateOfCreate(studentId, date, typeId, pairId, dateOfCreate);
        return (PointDTO) mapperService.toDto(entity);
    }

    /**
     * Перевод списка сущностей в список ДТО
     * @param points Список сущностей
     * @return Список ДТО
     */
    private List<PointDTO> entityListToDtoList(List<PointEntity> points) {
        List<PointDTO> modelList = new ArrayList<>();
        for (PointEntity item: points) {
            PointDTO model = (PointDTO) mapperService.toDto(item);
            modelList.add(model);
        }
        return modelList;
    }

    /**
     * Поучает последние установленные отметки
     * @param points Список отметок
     * @return Список отметок.
     */
    private List<PointEntity> getLastPoint(List<PointEntity> points) {
        List<PointEntity> result = new ArrayList<>();
        for (PointEntity point: points) {
            List<PointEntity> inOneDatePoint = points.stream().filter(x ->
                    x.getType().getId() == point.getType().getId()
                    && DateHelper.getZeroTimeDate(x.getDate()).compareTo(DateHelper.getZeroTimeDate(point.getDate())) == 0
                    && x.getPair().getId() == point.getPair().getId()
                    && x.getStudent().getId() == point.getStudent().getId()
            ).collect(Collectors.toList());

            if(inOneDatePoint.size() > 1) {
                PointEntity lastPoint = inOneDatePoint.stream().max(Comparator.comparing(PointEntity::getDateOfCreate)).get();
                if(!result.stream().anyMatch(x ->
                        x.getType().getId() == point.getType().getId()
                                && DateHelper.getZeroTimeDate(x.getDate()).compareTo(DateHelper.getZeroTimeDate(point.getDate())) == 0
                                && x.getPair().getId() == point.getPair().getId()
                                && x.getStudent().getId() == point.getStudent().getId()
                )) {
                    result.add(lastPoint);
                }
            } else {
                result.add(point);
            }
        }
        return result;
    }

    /**
     * Поучает установленные отметки до указанной даты
     * @param points Список отметок
     * @return Список отметок.
     */
    private List<PointEntity> getPointForDate(List<PointEntity> points, Date date) {
        points = points.stream().filter(x ->
                DateHelper.getZeroTimeDate(x.getDateOfCreate()).compareTo(DateHelper.getZeroTimeDate(date)) <= 0).collect(Collectors.toList());
        List<PointEntity> result = getLastPoint(points);
        return result;
    }

}

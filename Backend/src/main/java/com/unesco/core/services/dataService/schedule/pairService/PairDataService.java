package com.unesco.core.services.dataService.schedule.pairService;

import com.unesco.core.dto.additional.FilterQueryDTO;
import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.enums.StatusTypes;
import com.unesco.core.dto.shedule.LessonDTO;
import com.unesco.core.dto.shedule.PairDTO;
import com.unesco.core.entities.schedule.LessonEntity;
import com.unesco.core.entities.schedule.PairEntity;
import com.unesco.core.repositories.PairRepository;
import com.unesco.core.services.dataService.mapperService.IMapperService;
import com.unesco.core.services.dataService.schedule.lessonService.ILessonDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PairDataService implements IPairDataService {

    @Autowired
    private IMapperService mapperService;
    @Autowired
    private PairRepository pairRepository;
    @Autowired
    private ILessonDataService lessonDataService;

    public List<PairDTO> getPage(FilterQueryDTO filter) {
        int rows = filter.getRows()>0? filter.getRows() : (int) pairRepository.count();
        int start = rows>0 ? filter.getFirst()/rows: 1;
        List<PairEntity> entitys = pairRepository.findWithFilter(new PageRequest(start, rows == 0 ? 10 : rows), filter.getGlobalFilter());
        List<PairDTO> result = new ArrayList<PairDTO>();
        for (PairEntity e: entitys) {
            result.add((PairDTO) mapperService.toDto(e));
        }
        return result;
    }

    public List<PairDTO> getAll()
    {
        List<PairDTO> modelList = new ArrayList<>();
        Iterable<PairEntity> entityList = pairRepository.findAll();
        for (PairEntity item: entityList) {
            PairDTO model = (PairDTO) mapperService.toDto(item);
            modelList.add(model);
        }
        return modelList;
    }

    public List<PairDTO> getAllByProfessor(long professorId)
    {
        List<PairDTO> modelList = new ArrayList<>();
        Iterable<PairEntity> entityList = pairRepository.findPairsByProfessorId(professorId);
        for (PairEntity item: entityList) {
            PairDTO model = (PairDTO) mapperService.toDto(item);
            modelList.add(model);
        }
        return modelList;
    }

    public List<PairDTO> getAllByDepartament(long departmentId)
    {
        List<PairDTO> modelList = new ArrayList<>();
        Iterable<PairEntity> entityList = pairRepository.findPairsByDepartmentId(departmentId);
        for (PairEntity item: entityList) {
            PairDTO model = (PairDTO) mapperService.toDto(item);
            modelList.add(model);
        }
        return modelList;
    }

    public List<PairDTO> getAllByGroup(long groupId)
    {
        List<PairDTO> modelList = new ArrayList<>();
        Iterable<PairEntity> entityList = pairRepository.findPairsByGroupId(groupId);
        for (PairEntity item: entityList) {
            PairDTO model = (PairDTO) mapperService.toDto(item);
            modelList.add(model);
        }
        return modelList;
    }

    public List<PairDTO> getAllByLesson(long lessonId)
    {
        List<PairDTO> modelList = new ArrayList<>();
        Iterable<PairEntity> entityList = pairRepository.findPairsByLessonId(lessonId);
        for (PairEntity item: entityList) {
            PairDTO model = (PairDTO) mapperService.toDto(item);
            modelList.add(model);
        }
        return modelList;
    }

    public PairDTO get(long id)
    {
        PairEntity entity = pairRepository.findOne(id);
        PairDTO model = (PairDTO) mapperService.toDto(entity);
        return model;
    }

    public ResponseStatusDTO<PairDTO> delete(long id)
    {
        ResponseStatusDTO<PairDTO> result = new ResponseStatusDTO<>(StatusTypes.OK);
        PairDTO pairDTO = get(id);
        try {
            pairRepository.delete(id);
        } catch (Exception e) {
            result.setStatus(StatusTypes.ERROR);
            if(e instanceof DataIntegrityViolationException)
                result.addErrors("Удаление не удалось. У объекта есть зависимости.");
            else
                result.addErrors("Удаление не удалось");
            return result;
        }
        lessonDataService.delete(pairDTO.getLesson().getId());
        return result;
    }

    public ResponseStatusDTO<PairDTO> save(PairDTO pair)
    {
        PairEntity entity = (PairEntity) mapperService.toEntity(pair);
        ResponseStatusDTO<PairDTO> result = new ResponseStatusDTO<>(StatusTypes.OK);

        LessonDTO findLesson = lessonDataService.getDisciplineIdAndGroupIdAndProfessorId(
                entity.getLesson().getDiscipline().getId(),
                entity.getLesson().getGroup().getId(),
                entity.getLesson().getProfessor().getId());

        if(findLesson==null) {
            LessonDTO lesson = pair.getLesson();
            lesson.setId(0);
            pair.setLesson(lesson);
            ResponseStatusDTO<LessonDTO> saveLessonStatus = lessonDataService.save(pair.getLesson());
            if (saveLessonStatus.getStatus() == StatusTypes.ERROR)
            {
                result.setMessage(saveLessonStatus.getMessage());
                return result;
            }
            findLesson = saveLessonStatus.getData();
        }

        entity.setLesson((LessonEntity) mapperService.toEntity(findLesson));

        try {
            entity = pairRepository.save(entity);
        } catch (Exception e) {
            result.setStatus(StatusTypes.ERROR);
            result.addErrors(e.getMessage());
            return result;
        }
        result.setData((PairDTO) mapperService.toDto(entity));
        return result;
    }

    public List<PairDTO> findIntersections(PairDTO pairModel) {

        PairEntity entity = (PairEntity) mapperService.toEntity(pairModel);
        Set<PairEntity> allIntersections = new HashSet<>();

        // Проверка занятий на переcечение для проподавателя
        allIntersections.addAll(pairRepository.findPairsByDayofweekAndPairNumberAndWeektypeAndProfessor
                (entity.getDayofweek(), entity.getPairNumber(), entity.getWeektype(), entity.getLesson().getProfessor().getId()));

        // Проверка занятий на переcечение для аудиотрии
        allIntersections.addAll(pairRepository.findPairsByDayofweekAndPairNumberAndWeektypeAndRoom
                (entity.getDayofweek(), entity.getPairNumber(), entity.getWeektype(), entity.getRoom().getId()));

        // Проверка занятий на переcечение для группы
        allIntersections.addAll(pairRepository.findPairsByDayofweekAndPairNumberAndWeektypeAndGroup
                (entity.getDayofweek(), entity.getPairNumber(), entity.getWeektype(), entity.getLesson().getGroup().getId()));

        List<PairDTO> result = new ArrayList<>();
        for (PairEntity p: allIntersections) {
            result.add((PairDTO) mapperService.toDto(p));
        }
        return result;
    }
}

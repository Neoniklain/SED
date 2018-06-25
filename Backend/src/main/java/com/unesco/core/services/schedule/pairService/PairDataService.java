package com.unesco.core.services.schedule.pairService;

import com.unesco.core.entities.schedule.LessonEntity;
import com.unesco.core.entities.schedule.PairEntity;
import com.unesco.core.dto.additional.FilterQueryDTO;
import com.unesco.core.dto.shedule.LessonDTO;
import com.unesco.core.dto.shedule.PairDTO;
import com.unesco.core.repositories.PairRepository;
import com.unesco.core.repositories.account.ProfessorRepository;
import com.unesco.core.services.mapperService.IMapperService;
import com.unesco.core.services.schedule.lessonService.ILessonDataService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private ProfessorRepository professorRepository;

    public List<PairDTO> GetPage(FilterQueryDTO filter) {
        int rows = filter.getRows()>0? filter.getRows() : (int) pairRepository.count();
        int start = rows>0 ? filter.getFirst()/rows: 1;
        List<PairEntity> entitys = pairRepository.findWithFilter(new PageRequest(start, rows == 0 ? 10 : rows), filter.getGlobalFilter());
        List<PairDTO> result = new ArrayList<PairDTO>();
        for (PairEntity e: entitys) {
            result.add((PairDTO) mapperService.toDto(e));
        }
        return result;
    }

    public List<PairDTO> GetAll()
    {
        List<PairDTO> modelList = new ArrayList<>();
        Iterable<PairEntity> entityList = pairRepository.findAll();
        for (PairEntity item: entityList) {
            PairDTO model = (PairDTO) mapperService.toDto(item);
            modelList.add(model);
        }
        return modelList;
    }

    public List<PairDTO> GetAllByProfessor(long professorId)
    {
        List<PairDTO> modelList = new ArrayList<>();
        Iterable<PairEntity> entityList = pairRepository.findPairsByProfessorId(professorId);
        for (PairEntity item: entityList) {
            PairDTO model = (PairDTO) mapperService.toDto(item);
            modelList.add(model);
        }
        return modelList;
    }

    public List<PairDTO> GetAllByDepartament(long departmentId)
    {
        List<PairDTO> modelList = new ArrayList<>();
        Iterable<PairEntity> entityList = pairRepository.findPairsByDepartmentId(departmentId);
        for (PairEntity item: entityList) {
            PairDTO model = (PairDTO) mapperService.toDto(item);
            modelList.add(model);
        }
        return modelList;
    }

    public List<PairDTO> GetAllByGroup(long groupId)
    {
        List<PairDTO> modelList = new ArrayList<>();
        Iterable<PairEntity> entityList = pairRepository.findPairsByGroupId(groupId);
        for (PairEntity item: entityList) {
            PairDTO model = (PairDTO) mapperService.toDto(item);
            modelList.add(model);
        }
        return modelList;
    }

    public List<PairDTO> GetAllByLesson(long lessonId)
    {
        List<PairDTO> modelList = new ArrayList<>();
        Iterable<PairEntity> entityList = pairRepository.findPairsByLessonId(lessonId);
        for (PairEntity item: entityList) {
            PairDTO model = (PairDTO) mapperService.toDto(item);
            modelList.add(model);
        }
        return modelList;
    }

    public PairDTO Get(long id)
    {
        PairEntity entity = pairRepository.findOne(id);
        PairDTO model = (PairDTO) mapperService.toDto(entity);
        return model;
    }

    public void Delete(long id)
    {
        pairRepository.delete(id);
    }

    public PairDTO Save(PairDTO pair)
    {
        PairEntity entity = (PairEntity) mapperService.toEntity(pair);

        LessonDTO findLesson = lessonDataService.GetDisciplineIdAndGroupIdAndProfessorId(
                entity.getLessonEntity().getDisciplineEntity().getId(),
                entity.getLessonEntity().getGroupEntity().getId(),
                entity.getLessonEntity().getProfessorEntity().getId());

        if(findLesson==null) {
            LessonDTO lesson = pair.getLesson();
            lesson.setId(0);
            pair.setLesson(lesson);
            findLesson = lessonDataService.Save(pair.getLesson());
        }

        entity.setLessonEntity((LessonEntity) mapperService.toEntity(findLesson));

        PairEntity model = pairRepository.save(entity);
        pair = (PairDTO) mapperService.toDto(model);
        return pair;
    }

    public List<PairDTO> FindIntersections(PairDTO pairModel) {

        PairEntity entity = (PairEntity) mapperService.toEntity(pairModel);
        Set<PairEntity> allIntersections = new HashSet<>();

        // Проверка занятий на переcечение для проподавателя
        allIntersections.addAll(pairRepository.findPairsByDayofweekAndPairNumberAndWeektypeAndProfessor
                (entity.getDayofweek(), entity.getPairNumber(), entity.getWeektype(), entity.getLessonEntity().getProfessorEntity().getId()));

        // Проверка занятий на переcечение для аудиотрии
        allIntersections.addAll(pairRepository.findPairsByDayofweekAndPairNumberAndWeektypeAndRoom
                (entity.getDayofweek(), entity.getPairNumber(), entity.getWeektype(), entity.getRoomEntity().getId()));

        // Проверка занятий на переcечение для группы
        allIntersections.addAll(pairRepository.findPairsByDayofweekAndPairNumberAndWeektypeAndGroup
                (entity.getDayofweek(), entity.getPairNumber(), entity.getWeektype(), entity.getLessonEntity().getGroupEntity().getId()));

        List<PairDTO> result = new ArrayList<>();
        for (PairEntity p: allIntersections) {
            result.add((PairDTO) mapperService.toDto(p));
        }
        return result;
    }
}

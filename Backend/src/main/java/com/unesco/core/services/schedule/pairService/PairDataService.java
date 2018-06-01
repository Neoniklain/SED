package com.unesco.core.services.schedule.pairService;

import com.unesco.core.entities.schedule.Lesson;
import com.unesco.core.entities.schedule.Pair;
import com.unesco.core.models.additional.FilterQuery;
import com.unesco.core.models.shedule.PairModel;
import com.unesco.core.repositories.LessonRepository;
import com.unesco.core.repositories.PairRepository;
import com.unesco.core.repositories.account.ProfessorRepository;
import com.unesco.core.services.mapperService.IMapperService;
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
    private LessonRepository lessonRepository;
    @Autowired
    private ProfessorRepository professorRepository;

    public List<PairModel> GetPage(FilterQuery filter) {
        int rows = filter.getRows()>0? filter.getRows() : (int) pairRepository.count();
        int start = rows>0 ? filter.getFirst()/rows: 1;
        List<Pair> entitys = pairRepository.findWithFilter(new PageRequest(start, rows == 0 ? 10 : rows), filter.getGlobalFilter());
        List<PairModel> result = new ArrayList<PairModel>();
        for (Pair e: entitys) {
            result.add((PairModel) mapperService.toModel(e));
        }
        return result;
    }

    public List<PairModel> GetAll()
    {
        List<PairModel> modelList = new ArrayList<>();
        Iterable<Pair> entityList = pairRepository.findAll();
        for (Pair item: entityList) {
            PairModel model = (PairModel) mapperService.toModel(item);
            modelList.add(model);
        }
        return modelList;
    }

    public List<PairModel> GetAllByProfessor(long professorId)
    {
        List<PairModel> modelList = new ArrayList<>();
        Iterable<Pair> entityList = pairRepository.findPairsByProfessorId(professorRepository.findByUserId(professorId).getId());
        for (Pair item: entityList) {
            PairModel model = (PairModel) mapperService.toModel(item);
            modelList.add(model);
        }
        return modelList;
    }

    public List<PairModel> GetAllByDepartament(long departmentId)
    {
        List<PairModel> modelList = new ArrayList<>();
        Iterable<Pair> entityList = pairRepository.findPairsByDepartmentId(departmentId);
        for (Pair item: entityList) {
            PairModel model = (PairModel) mapperService.toModel(item);
            modelList.add(model);
        }
        return modelList;
    }

    public List<PairModel> GetAllByGroup(long groupId)
    {
        List<PairModel> modelList = new ArrayList<>();
        Iterable<Pair> entityList = pairRepository.findPairsByGroupId(groupId);
        for (Pair item: entityList) {
            PairModel model = (PairModel) mapperService.toModel(item);
            modelList.add(model);
        }
        return modelList;
    }

    public List<PairModel> GetAllByLesson(long lessonId)
    {
        List<PairModel> modelList = new ArrayList<>();
        Iterable<Pair> entityList = pairRepository.findPairsByLessonId(lessonId);
        for (Pair item: entityList) {
            PairModel model = (PairModel) mapperService.toModel(item);
            modelList.add(model);
        }
        return modelList;
    }

    public PairModel Get(long id)
    {
        Pair entity = pairRepository.findOne(id);
        PairModel model = (PairModel) mapperService.toModel(entity);
        return model;
    }

    public void Delete(long id)
    {
        pairRepository.delete(id);
    }

    public PairModel Save(PairModel pair)
    {
        Pair entity = (Pair) mapperService.toEntity(pair);

        Lesson findLesson = lessonRepository.findByDisciplineIdAndGroupIdAndProfessorId(
                entity.getLesson().getDiscipline().getId(),
                entity.getLesson().getGroup().getId(),
                entity.getLesson().getProfessor().getId());

        if(findLesson==null) {
            findLesson = (Lesson) mapperService.toEntity(
                    lessonRepository.save(entity.getLesson()));
        }
        entity.setLesson(findLesson);

        Pair model = pairRepository.save(entity);
        pair = (PairModel) mapperService.toModel(model);
        return pair;
    }

    public List<PairModel> FindIntersections(PairModel pairModel) {

        Pair entity = (Pair) mapperService.toEntity(pairModel);
        Set<Pair> allIntersections = new HashSet<>();

        // Проверка занятий на переcечение для проподавателя
        allIntersections.addAll(pairRepository.findPairsByDayofweekAndPairNumberAndWeektypeAndProfessor
                (entity.getDayofweek(), entity.getPairNumber(), entity.getWeektype(), entity.getLesson().getProfessor().getId()));

        // Проверка занятий на переcечение для аудиотрии
        allIntersections.addAll(pairRepository.findPairsByDayofweekAndPairNumberAndWeektypeAndRoom
                (entity.getDayofweek(), entity.getPairNumber(), entity.getWeektype(), entity.getRoom().getId()));

        // Проверка занятий на переcечение для группы
        allIntersections.addAll(pairRepository.findPairsByDayofweekAndPairNumberAndWeektypeAndGroup
                (entity.getDayofweek(), entity.getPairNumber(), entity.getWeektype(), entity.getLesson().getGroup().getId()));

        List<PairModel> result = new ArrayList<>();
        for (Pair p: allIntersections) {
            result.add((PairModel) mapperService.toModel(p));
        }
        return result;
    }
}

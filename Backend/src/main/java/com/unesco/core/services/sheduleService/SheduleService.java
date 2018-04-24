package com.unesco.core.services.sheduleService;

import com.unesco.core.entities.Discipline;
import com.unesco.core.entities.Group;
import com.unesco.core.entities.Professor;
import com.unesco.core.entities.schedule.Pair;
import com.unesco.core.models.*;
import com.unesco.core.models.additional.JSONResponseStatus;
import com.unesco.core.repositories.PairRepository;
import com.unesco.core.repositories.account.ProfessorRepository;
import com.unesco.core.services.dictionaryDataService.DitionaryDataService;
import com.unesco.core.services.mapperService.IMapperService;
import com.unesco.core.services.userService.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SheduleService implements ISheduleService{
    @Autowired
    private IMapperService mapperService;
    @Autowired
    PairRepository pairRepository;
    @Autowired
    private IUserService userService;
    @Autowired
    private ProfessorRepository professorRepository;
    @Autowired
    private DitionaryDataService ditionaryDataService;

    public List<PairModel> getPairs(ProfessorModel professor)
    {
        Professor professorEntity = professorRepository.findOne((long) professor.getId());
        Iterable<Pair> pairs = pairRepository.findPairsByProfessor(professorEntity);
        List<PairModel> result = new ArrayList<PairModel>();
        for (Pair p : pairs) {
            result.add((PairModel) mapperService.toModel(p));
        }
        return result;
    }
    public List<PairModel> getOddPairs(ProfessorModel professor)
    {
        Professor professorEntity = professorRepository.findOne((long) professor.getId());
        Iterable<Pair> pairs = pairRepository.findPairsByProfessor(professorEntity);
        List<PairModel> result = new ArrayList<PairModel>();
        for (Pair p : pairs) {
            if (p.getWeektype().equals("Нечет"))
                result.add((PairModel) mapperService.toModel(p));
        }
        return result;
    }
    public List<PairModel> getEvenPairs(ProfessorModel professor)
    {
        Professor professorEntity = professorRepository.findOne((long) professor.getId());
        Iterable<Pair> pairs = pairRepository.findPairsByProfessor(professorEntity);
        List<PairModel> result = new ArrayList<PairModel>();
        for (Pair p : pairs) {
            if (p.getWeektype().equals("Чет"))
                result.add((PairModel) mapperService.toModel(p));
        }
        return result;
    }

    public List<PairModel> getPairs(GroupModel group)
    {
        Iterable<Pair> pairs = pairRepository.findPairsByGroup((Group) mapperService.toEntity(group));
        List<PairModel> result = new ArrayList<PairModel>();
        for (Pair p : pairs) {
            result.add((PairModel) mapperService.toModel(p));
        }
        return result;
    }
    public List<PairModel> getOddPairs(GroupModel group)
    {
        Iterable<Pair> pairs = pairRepository.findPairsByGroup((Group) mapperService.toEntity(group));
        List<PairModel> result = new ArrayList<PairModel>();
        for (Pair p : pairs) {
            if (p.getWeektype().equals("Чет")) {
                result.add((PairModel) mapperService.toModel(p));
            }
        }
        return result;
    }
    public List<PairModel> getEvenPairs(GroupModel group)
    {
        Iterable<Pair> pairs = pairRepository.findPairsByGroup((Group) mapperService.toEntity(group));
        List<PairModel> result = new ArrayList<PairModel>();
        for (Pair p : pairs) {
            if (p.getWeektype().equals("Нечет")) {
                result.add((PairModel) mapperService.toModel(p));
            }
        }
        return result;
    }

    public List<DepartmentSheduleModel> getPairs(DepartmentModel department)
    {
        List<ProfessorModel> professors = userService.getProfessors();

        List<DepartmentSheduleModel> result = new ArrayList<DepartmentSheduleModel>();

        for(ProfessorModel proffesor : professors) {
            DepartmentSheduleModel shedule = new DepartmentSheduleModel();
            List<PairModel> pairsList = getPairs(proffesor);
            shedule.setProfessor(proffesor);
            shedule.setPairs(pairsList);
            result.add(shedule);
        }

        return result;
    }

    public List<DepartmentSheduleModel> getOddPairs(DepartmentModel department)
    {
        List<ProfessorModel> professors = userService.getProfessors();

        List<DepartmentSheduleModel> result = new ArrayList<DepartmentSheduleModel>();

        for(ProfessorModel proffesor : professors) {
            DepartmentSheduleModel shedule = new DepartmentSheduleModel();
            List<PairModel> pairsList = getEvenPairs(proffesor);
            shedule.setProfessor(proffesor);
            shedule.setPairs(pairsList);
            result.add(shedule);
        }

        return result;
    }

    public List<DepartmentSheduleModel> getEvenPairs(DepartmentModel department)
    {
        List<ProfessorModel> professors = userService.getProfessors();

        List<DepartmentSheduleModel> result = new ArrayList<DepartmentSheduleModel>();

        for(ProfessorModel proffesor : professors) {
            DepartmentSheduleModel shedule = new DepartmentSheduleModel();
            List<PairModel> pairsList = getOddPairs(proffesor);
            shedule.setProfessor(proffesor);
            shedule.setPairs(pairsList);
            result.add(shedule);
        }

        return result;
    }

    public PairModel getPair(int id)
    {
        Pair pair = pairRepository.findOne((long) id);
        return (PairModel) mapperService.toModel(pair);
    }

    public PairModel getPair(int proffesorId, int groupId, int disciplineId)
    {
        Professor professor = professorRepository.findOne((long) proffesorId);
        GroupModel group = ditionaryDataService.getGroup(groupId);
        DisciplineModel discipline = ditionaryDataService.getDiscipline(disciplineId);
        Pair pair = pairRepository.findPairsByGroupAndProfessorAndDiscipline(
                (Group) mapperService.toEntity(group),
                professor,
                (Discipline) mapperService.toEntity(discipline));
        PairModel result = (PairModel) mapperService.toModel(pair);
        return result;
    }

    public JSONResponseStatus deletePair(int id)
    {
        try { pairRepository.delete((long)id); }
        catch (Exception e){ return JSONResponseStatus.ERROR(); }
        return JSONResponseStatus.OK();
    }

    public JSONResponseStatus savePair(PairModel pairModel)
    {
        Pair entity = (Pair) mapperService.toEntity(pairModel);
        entity.setProfessor(professorRepository.findOne(entity.getProfessor().getUser().getId()));

        List<Pair> check = new ArrayList<>();
        // Проверка занятий на переcечение для проподавателя
        check = pairRepository.findPairsByDayofweekAndPairNumberAndWeektypeAndProfessor
                (entity.getDayofweek(), entity.getPairNumber(), entity.getWeektype(), entity.getProfessor());
        if (!check.isEmpty())
            return JSONResponseStatus.ERROR("Для преподавателя "+check.get(0).getProfessor().getUser().getUserFIO()
                    +" уже назначено занятие в это время у группы "+check.get(0).getGroup().getName());
        // Проверка занятий на переcечение для аудиотрии
        check = pairRepository.findPairsByDayofweekAndPairNumberAndWeektypeAndRoom
                (entity.getDayofweek(), entity.getPairNumber(), entity.getWeektype(), entity.getRoom());
        if (!check.isEmpty())
            return JSONResponseStatus.ERROR("В аудитории "+check.get(0).getRoom().getRoom()
                    +" уже назначено занятие в это время у преподавателя "+check.get(0).getProfessor().getUser().getUserFIO());
        // Проверка занятий на переcечение для группы
        check = pairRepository.findPairsByDayofweekAndPairNumberAndWeektypeAndRoom
                (entity.getDayofweek(), entity.getPairNumber(), entity.getWeektype(), entity.getRoom());
        if (!check.isEmpty())
            return JSONResponseStatus.ERROR("У группы "+check.get(0).getGroup().getName()
                    +" уже назначено занятие в это время у преподавателя "+check.get(0).getProfessor().getUser().getUserFIO());

        try {
            pairRepository.save(entity);
        }
        catch (Exception e){
            return JSONResponseStatus.ERROR();
        }
        return JSONResponseStatus.OK();
    }
}

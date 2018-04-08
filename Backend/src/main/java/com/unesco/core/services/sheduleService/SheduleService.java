package com.unesco.core.services.sheduleService;

import com.unesco.core.entities.Group;
import com.unesco.core.entities.Professor;
import com.unesco.core.entities.schedule.Pair;
import com.unesco.core.models.DepartmentModel;
import com.unesco.core.models.GroupModel;
import com.unesco.core.models.PairModel;
import com.unesco.core.models.ProfessorModel;
import com.unesco.core.repositories.PairRepository;
import com.unesco.core.repositories.account.ProfessorRepository;
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
            if (p.getWeektype().getType().equals("Нечет"))
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
            if (p.getWeektype().getType().equals("Чет"))
                result.add((PairModel) mapperService.toModel(p));
        }
        return result;
    }

    public List<PairModel> getOddPairs(GroupModel group)
    {
        Iterable<Pair> pairs = pairRepository.findPairsByGroup((Group) mapperService.toEntity(group));
        List<PairModel> result = new ArrayList<PairModel>();
        for (Pair p : pairs) {
            if (p.getWeektype().getType().equals("Чет")) {
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
            if (p.getWeektype().getType().equals("Нечет")) {
                result.add((PairModel) mapperService.toModel(p));
            }
        }
        return result;
    }

    public Map<ProfessorModel, List<PairModel>> getOddPairs(DepartmentModel department)
    {
        List<ProfessorModel> professors = userService.getProfessors();

        Map<ProfessorModel, List<PairModel>> pairsMap = new HashMap<>();

        for(ProfessorModel proffesor : professors) {
            List<PairModel> pairsList = getEvenPairs(proffesor);
            pairsMap.put(proffesor, pairsList);
        }

        return pairsMap;
    }
    public Map<ProfessorModel, List<PairModel>> getEvenPairs(DepartmentModel department)
    {
        List<ProfessorModel> professors = userService.getProfessors();

        Map<ProfessorModel, List<PairModel>> pairsMap = new HashMap<>();

        for(ProfessorModel proffesor : professors) {
            List<PairModel> pairsList = getOddPairs(proffesor);
            pairsMap.put(proffesor, pairsList);
        }

        return pairsMap;
    }

    public PairModel getPair(int id)
    {
        Pair pair = pairRepository.findOne((long) id);
        return (PairModel) mapperService.toModel(pair);
    }
}

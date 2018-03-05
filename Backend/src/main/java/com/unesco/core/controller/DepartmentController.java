package com.unesco.core.controller;

import com.unesco.core.models.PairModel;
import com.unesco.core.entities.Professor;
import com.unesco.core.entities.schedule.Pair;
import com.unesco.core.repositories.PairRepository;
import com.unesco.core.repositories.ProfessorRepository;
import com.unesco.core.repositories.plan.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@CrossOrigin
@RestController
@RequestMapping("/api/demo")
public class DepartmentController {
    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    PairRepository pairRepository;

    @Autowired
    ProfessorRepository professorRepository;

    /*
    Оптимизацией кода займусь позже, главное, что работает.
     */

    @RequestMapping("/department/even")
    public Map<Professor, List<PairModel>> getDepartmentSheduleChet(/*@PathVariable("id") Long id*/) {
        //Department department = departmentRepository.findById(id);
        List<Professor> professors = StreamSupport
                .stream(professorRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        Map<Professor, List<PairModel>> pairsMap = new HashMap<>();
        List<PairModel> pairsList = new ArrayList<>();
        for(Professor p : professors) {
            List <Pair> pairs = pairRepository.findPairsByProfessor(p);
            for(Pair pair : pairs) {
                if(pair.getWeektype().getType().equals("Чет"))
                    pairsList.add(new PairModel(pair.getPairNumber(), pair.getWeektype().getType(),
                            pair.getDayofweek().getDayofweek(), pair.getProfessor().getFio(),
                            pair.getRoom().getRoom(), pair.getDiscipline().getName(), pair.getGroup().getName()));
            }
            pairsMap.put(p, pairsList);
        }

        return pairsMap;
    }

    @RequestMapping("/department/odd")
    public Map<Professor, List<PairModel>> getDepartmentSheduleNechet(/*@PathVariable("id") Long id*/) {
        //Department department = departmentRepository.findById(id);
        List<Professor> professors = StreamSupport
                .stream(professorRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        Map<Professor, List<PairModel>> pairsMap = new HashMap<>();
        List<PairModel> pairsList = new ArrayList<>();
        for(Professor p : professors) {
            List <Pair> pairs = pairRepository.findPairsByProfessor(p);
            for(Pair pair : pairs) {
                if(pair.getWeektype().getType().equals("Нечет"))
                    pairsList.add(new PairModel(pair.getPairNumber(), pair.getWeektype().getType(),
                            pair.getDayofweek().getDayofweek(), pair.getProfessor().getFio(),
                            pair.getRoom().getRoom(), pair.getDiscipline().getName(), pair.getGroup().getName()));
            }
            pairsMap.put(p, pairsList);
        }

        return pairsMap;
    }

}

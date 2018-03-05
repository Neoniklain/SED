package com.unesco.core.controller;

import com.unesco.core.ViewModel.PairViewModel;
import com.unesco.core.entities.Professor;
import com.unesco.core.entities.schedule.Pair;
import com.unesco.core.repositories.PairRepository;
import com.unesco.core.repositories.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/demo")
public class ProfessorController {
    @Autowired
    ProfessorRepository professorRepository;

    @Autowired
    PairRepository pairRepository;

    @RequestMapping("/professor/{id}/pairs/even")
    public List<PairViewModel> getChetPairs(@PathVariable("id") int id) {
        Professor professor = professorRepository.findOne((long) id);
        Iterable<Pair> pairs = pairRepository.findPairsByProfessor(professor);
        List<PairViewModel> chetPairList = new ArrayList<PairViewModel>();
        for (Pair p : pairs) {
            if (p.getWeektype().getType().equals("Чет")) {
                chetPairList.add(new PairViewModel(p.getPairNumber(), p.getWeektype().getType(),
                        p.getDayofweek().getDayofweek(), p.getProfessor().getFio(),
                        p.getRoom().getRoom(), p.getDiscipline().getName(), p.getGroup().getName()));
            }
        }
        return chetPairList;
    }

    @RequestMapping("/professor/{id}/pairs/odd")
    public List<PairViewModel> getNechetPairs(@PathVariable("id") int id) {
        Professor professor = professorRepository.findOne((long) id);
        Iterable<Pair> pairs = pairRepository.findPairsByProfessor(professor);
        List<PairViewModel> nechetPairList = new ArrayList<PairViewModel>();
        for (Pair p : pairs) {
            if (p.getWeektype().getType().equals("Нечет")) {
                nechetPairList.add(new PairViewModel(p.getPairNumber(), p.getWeektype().getType(),
                        p.getDayofweek().getDayofweek(), p.getProfessor().getFio(),
                        p.getRoom().getRoom(), p.getDiscipline().getName(), p.getGroup().getName()));
            }
        }
        return nechetPairList;
    }

    @RequestMapping("/professors")
    public List<Professor> getProfessors() {
        Iterable<Professor> tmp = professorRepository.findAll();
        return StreamSupport.stream(tmp.spliterator(), false).collect(Collectors.toList());
    }

    @RequestMapping("/professor/{id}")
    public Professor getProfessor(@PathVariable("id") Long id) {
        return professorRepository.findOne(id);
    }
}
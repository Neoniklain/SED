package com.unesco.core.controller;

import com.unesco.core.ViewModel.PairViewModel;
import com.unesco.core.entities.Pair;
import com.unesco.core.entities.Professor;
import com.unesco.core.entities.WeekType;
import com.unesco.core.repositories.PairRepository;
import com.unesco.core.repositories.ProfessorRepository;
import com.unesco.core.repositories.WeekTypeRepository;
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
public class PairController {
    @Autowired
    public PairRepository pairRepository;

    @Autowired
    public ProfessorRepository professorRepository;

    @RequestMapping("/professor/{id}/pairs/even")
    public List<PairViewModel> getChetPairs(@PathVariable("id") int id) {
        Professor professor = professorRepository.findOne((long)id);
        Iterable<Pair> pairs = pairRepository.findPairsByProfessor(professor);
        List<PairViewModel> chetPairList = new ArrayList<PairViewModel>();
        for(Pair p : pairs) {
            if(p.getWeektype().getType().equals("Чет")) {
                chetPairList.add(new PairViewModel(p.getPairNumber(), p.getWeektype().getType(),
                        p.getDayofweek().getDayofweek(), p.getProfessor().getFio(),
                        p.getRoom().getRoom(), p.getDiscipline().getName(), p.getGroup().getName()));
            }
        }
        return chetPairList;
    }

    @RequestMapping("/professor/{id}/pairs/odd")
    public List<PairViewModel> getNechetPairs(@PathVariable("id") int id) {
        Professor professor = professorRepository.findOne((long)id);
        Iterable<Pair> pairs = pairRepository.findPairsByProfessor(professor);
        List<PairViewModel> nechetPairList = new ArrayList<PairViewModel>();
        for(Pair p : pairs) {
            if(p.getWeektype().getType().equals("Нечет")) {
                nechetPairList.add(new PairViewModel(p.getPairNumber(), p.getWeektype().getType(),
                        p.getDayofweek().getDayofweek(), p.getProfessor().getFio(),
                        p.getRoom().getRoom(), p.getDiscipline().getName(), p.getGroup().getName()));
            }
        }
        return nechetPairList;
    }

    @RequestMapping("/pairs/{id}")
    public Pair getPair(@PathVariable("id") int id) {
        return pairRepository.findById(id);
    }
}

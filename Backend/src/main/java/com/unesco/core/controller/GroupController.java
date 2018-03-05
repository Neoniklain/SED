package com.unesco.core.controller;

import com.unesco.core.ViewModel.PairViewModel;
import com.unesco.core.entities.Department;
import com.unesco.core.entities.Group;
import com.unesco.core.entities.Pair;
import com.unesco.core.repositories.DepartmentRepository;
import com.unesco.core.repositories.GroupRepository;
import com.unesco.core.repositories.PairRepository;
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
public class GroupController {
    @Autowired
    PairRepository pairRepository;

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @RequestMapping("/group/{id}/pairs/even")
    public List<PairViewModel> getChetPairsForGroup(@PathVariable("id") Long id) {
        Group group = groupRepository.findOne(id);
        Iterable<Pair> pairs = pairRepository.findPairsByGroup(group);
        Department department;
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

    @RequestMapping("/group/{id}/pairs/odd")
    public List<PairViewModel> getNechetPairsForGroup(@PathVariable("id") Long id) {
        Group group = groupRepository.findOne(id);
        Iterable<Pair> pairs = pairRepository.findPairsByGroup(group);
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

    @RequestMapping("/groups")
    public List<Group> getGroups() {
        Iterable<Group> tmp = groupRepository.findAll();
        return StreamSupport.stream(tmp.spliterator(), false).collect(Collectors.toList());
    }
}

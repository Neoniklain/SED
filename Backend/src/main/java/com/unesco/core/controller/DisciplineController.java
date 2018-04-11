package com.unesco.core.controller;

import com.unesco.core.entities.Discipline;
import com.unesco.core.repositories.plan.DisciplineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/demo")
public class DisciplineController {
    @Autowired
    private DisciplineRepository disciplineRepository;

    @RequestMapping("/disciplines")
    public List<Discipline> getDisciplines() {
        Iterable<Discipline> tmp = disciplineRepository.findAll();
        return StreamSupport.stream(tmp.spliterator(), false).collect(Collectors.toList());
    }
}

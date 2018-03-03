package com.unesco.core.controller;

import com.unesco.core.entities.Professor;
import com.unesco.core.entities.User;
import com.unesco.core.repositories.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/demo")
public class ProfessorController {
    @Autowired
    ProfessorRepository professorRepository;

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
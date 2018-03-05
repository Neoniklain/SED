package com.unesco.core.controller;

import com.unesco.core.entities.Pair;
import com.unesco.core.repositories.PairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/demo")
public class PairController {
    @Autowired
    public PairRepository pairRepository;

    @RequestMapping("/pairs/{id}")
    public Pair getPair(@PathVariable("id") int id) {
        return pairRepository.findById(id);
    }


}

package com.unesco.core.repositories;

import com.unesco.core.entities.Pair;
import com.unesco.core.entities.Professor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PairRepository extends CrudRepository<Pair, Integer> {
    Pair findById(int id);
    List<Pair> findPairsByProfessor(Professor professor);
}

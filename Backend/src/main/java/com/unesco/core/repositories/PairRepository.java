package com.unesco.core.repositories;

import com.unesco.core.entities.Group;
import com.unesco.core.entities.schedule.Pair;
import com.unesco.core.entities.Professor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PairRepository extends CrudRepository<Pair, Integer> {
    Pair findById(int id);
    List<Pair> findPairsByProfessor(Professor professor);
    List<Pair> findPairsByGroup(Group group);
    List<Pair> findPairsByProfessorFio(String fio);

}

package com.unesco.core.repositories;

import com.unesco.core.entities.Group;
import com.unesco.core.entities.plan.Semester;
import com.unesco.core.entities.schedule.Pair;
import com.unesco.core.entities.Professor;
import com.unesco.core.repositories.utils.CrudPagableRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PairRepository extends CrudRepository<Pair, Long>, CrudPagableRepository<Pair, Long> {
    Pair findById(int id);
    List<Pair> findPairsByProfessor(Professor professor);
    List<Pair> findPairsByGroup(Group group);
    List<Pair> findPairsByProfessorFio(String fio);
}

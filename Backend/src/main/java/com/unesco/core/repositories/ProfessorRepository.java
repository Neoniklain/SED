package com.unesco.core.repositories;

import com.unesco.core.entities.Professor;
import com.unesco.core.entities.schedule.Pair;
import com.unesco.core.repositories.utils.CrudPagableRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface ProfessorRepository extends CrudRepository<Professor, Long>, CrudPagableRepository<Professor, Long> {
    Professor findByFio(String fio);
}

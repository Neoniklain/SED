package com.unesco.core.repositories;

import com.unesco.core.entities.Discipline;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DisciplineRepository extends CrudRepository<Discipline, Long> {
    Discipline findById(long id);
    Page findAll(Pageable pageRequest);
}
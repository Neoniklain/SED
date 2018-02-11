package com.unesco.core.repositories;

import com.unesco.core.entities.Discipline;
import com.unesco.core.entities.News;
import org.springframework.data.repository.CrudRepository;

public interface DisciplineRepository extends CrudRepository<Discipline, Long> {
    Discipline findById(long id);
}

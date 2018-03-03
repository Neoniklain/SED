package com.unesco.core.repositories;

import com.unesco.core.entities.Professor;
import org.springframework.data.repository.CrudRepository;

public interface ProfessorRepository extends CrudRepository<Professor, Long> {
    Professor findByFio(String fio);
}

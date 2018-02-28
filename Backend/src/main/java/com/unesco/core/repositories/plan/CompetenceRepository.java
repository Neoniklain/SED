package com.unesco.core.repositories.plan;

import com.unesco.core.entities.Competence;
import org.springframework.data.repository.CrudRepository;

public interface CompetenceRepository extends CrudRepository<Competence, Long> {
    Competence findByCode(String code);
}

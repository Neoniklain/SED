package com.unesco.core.repositories.plan;

import com.unesco.core.entities.FieldOfKnowledge;
import com.unesco.core.repositories.utils.CrudPagableRepository;
import org.springframework.data.repository.CrudRepository;

public interface FieldOfKnowledgeRepository extends CrudRepository<FieldOfKnowledge, Long>, CrudPagableRepository<FieldOfKnowledge, Long> {
   FieldOfKnowledge findByName(String name);
}

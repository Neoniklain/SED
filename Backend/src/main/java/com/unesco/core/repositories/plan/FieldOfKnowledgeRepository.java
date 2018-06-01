package com.unesco.core.repositories.plan;

import com.unesco.core.entities.schedule.FieldOfKnowledge;
import com.unesco.core.repositories.utils.CrudPagableRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FieldOfKnowledgeRepository extends CrudRepository<FieldOfKnowledge, Long>, CrudPagableRepository<FieldOfKnowledge, Long> {
   FieldOfKnowledge findByName(String name);

   @Query("SELECT f FROM FieldOfKnowledge f where lower(f.name) LIKE CONCAT('%',lower(:filter),'%')")
   List<FieldOfKnowledge> findWithFilter(Pageable pageable, @Param("filter")  String filter);
}

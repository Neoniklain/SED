package com.unesco.core.repositories.plan;

import com.unesco.core.entities.schedule.FieldOfKnowledgeEntity;
import com.unesco.core.repositories.utils.CrudPagableRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FieldOfKnowledgeRepository extends CrudRepository<FieldOfKnowledgeEntity, Long>, CrudPagableRepository<FieldOfKnowledgeEntity, Long> {
   FieldOfKnowledgeEntity findByName(String name);

   @Query("SELECT f FROM FieldOfKnowledgeEntity f where lower(f.name) LIKE CONCAT('%',lower(:filter),'%')")
   List<FieldOfKnowledgeEntity> findWithFilter(Pageable pageable, @Param("filter")  String filter);
}

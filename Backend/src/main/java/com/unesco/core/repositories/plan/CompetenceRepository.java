package com.unesco.core.repositories.plan;

import com.unesco.core.entities.plan.Competence;
import com.unesco.core.repositories.utils.CrudPagableRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CompetenceRepository extends CrudRepository<Competence, Long>, CrudPagableRepository<Competence, Long> {
    Competence findByCode(String code);

    @Query("SELECT c FROM Competence c where lower(c.code) LIKE CONCAT('%',lower(:filter),'%')")
    List<Competence> findWithFilter(Pageable pageable, @Param("filter")  String filter);
}

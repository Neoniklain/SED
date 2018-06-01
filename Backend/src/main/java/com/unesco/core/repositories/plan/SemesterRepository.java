package com.unesco.core.repositories.plan;

import com.unesco.core.entities.plan.Competence;
import com.unesco.core.entities.plan.Semester;
import com.unesco.core.repositories.utils.CrudPagableRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SemesterRepository extends CrudRepository<Semester, Long>, CrudPagableRepository<Semester, Long> {
    Page findAll(Pageable pageRequest);
    public Semester findByPlanId(Long planId);

    @Query("SELECT s FROM Semester s")
    List<Semester> findWithFilter(Pageable pageable, @Param("filter")  String filter);
}

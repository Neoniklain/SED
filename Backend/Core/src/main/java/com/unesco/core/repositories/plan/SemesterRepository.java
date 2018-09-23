package com.unesco.core.repositories.plan;

import com.unesco.core.entities.plan.SemesterEntity;
import com.unesco.core.repositories.utils.CrudPagableRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SemesterRepository extends CrudRepository<SemesterEntity, Long>, CrudPagableRepository<SemesterEntity, Long> {
    Page findAll(Pageable pageRequest);
    public SemesterEntity findByPlanId(Long planId);

    @Query("SELECT s FROM SemesterEntity s")
    List<SemesterEntity> findWithFilter(Pageable pageable, @Param("filter")  String filter);
}

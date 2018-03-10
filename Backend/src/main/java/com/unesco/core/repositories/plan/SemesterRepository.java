package com.unesco.core.repositories.plan;

import com.unesco.core.entities.plan.Plan;
import com.unesco.core.entities.plan.Semester;
import com.unesco.core.repositories.utils.CrudPagableRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface SemesterRepository extends CrudRepository<Semester, Long>, CrudPagableRepository<Semester, Long> {
    Page findAll(Pageable pageRequest);
    public Semester findByPlanId(Long planId);
}

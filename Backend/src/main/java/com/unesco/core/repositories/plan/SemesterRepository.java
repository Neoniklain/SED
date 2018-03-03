package com.unesco.core.repositories.plan;

import com.unesco.core.entities.plan.Semester;
import org.springframework.data.repository.CrudRepository;

public interface SemesterRepository extends CrudRepository<Semester, Long> {
    public Semester findByPlanId(Long planId);
}

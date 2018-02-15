package com.unesco.core.repositories;

import com.unesco.core.entities.Plan;
import com.unesco.core.entities.Semester;
import org.springframework.data.repository.CrudRepository;

public interface SemesterRepository extends CrudRepository<Semester, Long> {
    Semester findByPlans(Plan item);
}

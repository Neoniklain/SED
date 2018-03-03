package com.unesco.core.repositories.plan;

import com.unesco.core.entities.Discipline;
import com.unesco.core.entities.plan.Plan;
import org.springframework.data.repository.CrudRepository;

public interface PlanRepository extends CrudRepository<Plan, Long> {
    Plan findByDiscipline(Discipline item);
}

package com.unesco.core.repositories.plan;

import com.unesco.core.entities.Discipline;
import com.unesco.core.entities.plan.LessonType;
import com.unesco.core.entities.plan.Plan;
import com.unesco.core.repositories.utils.CrudPagableRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface PlanRepository extends CrudRepository<Plan, Long>, CrudPagableRepository<Plan, Long> {
    Plan findByDiscipline(Discipline item);
}

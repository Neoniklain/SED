package com.unesco.core.repositories.plan;

import com.unesco.core.entities.schedule.Discipline;
import com.unesco.core.entities.plan.Plan;
import com.unesco.core.repositories.utils.CrudPagableRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlanRepository extends CrudRepository<Plan, Long>, CrudPagableRepository<Plan, Long> {
    Plan findByDiscipline(Discipline item);

    @Query("SELECT p FROM Plan p")
    List<Plan> findWithFilter(Pageable pageable, @Param("filter")  String filter);
}

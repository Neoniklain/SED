package com.unesco.core.repositories.plan;

import com.unesco.core.entities.plan.PlanEntity;
import com.unesco.core.entities.schedule.DisciplineEntity;
import com.unesco.core.repositories.utils.CrudPagableRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlanRepository extends CrudRepository<PlanEntity, Long>, CrudPagableRepository<PlanEntity, Long> {
    PlanEntity findByDiscipline(DisciplineEntity item);

    @Query("SELECT p FROM PlanEntity p")
    List<PlanEntity> findWithFilter(Pageable pageable, @Param("filter")  String filter);
}

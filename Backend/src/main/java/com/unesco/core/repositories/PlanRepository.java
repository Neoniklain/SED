package com.unesco.core.repositories;

import com.unesco.core.entities.Plan;
import org.springframework.data.repository.CrudRepository;

public interface PlanRepository extends CrudRepository<Plan, Long> {
}
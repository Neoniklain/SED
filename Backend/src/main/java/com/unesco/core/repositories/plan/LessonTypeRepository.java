package com.unesco.core.repositories.plan;

import com.unesco.core.entities.plan.LessonTypeEntity;
import com.unesco.core.repositories.utils.CrudPagableRepository;
import org.springframework.data.repository.CrudRepository;

public interface LessonTypeRepository extends CrudRepository<LessonTypeEntity, Long>, CrudPagableRepository<LessonTypeEntity, Long> {
}

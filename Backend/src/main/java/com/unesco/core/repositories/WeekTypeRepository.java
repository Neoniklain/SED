package com.unesco.core.repositories;

import com.unesco.core.entities.schedule.WeekType;
import com.unesco.core.repositories.utils.CrudPagableRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface WeekTypeRepository extends CrudRepository<WeekType, Long>, CrudPagableRepository<WeekType, Long> {

}

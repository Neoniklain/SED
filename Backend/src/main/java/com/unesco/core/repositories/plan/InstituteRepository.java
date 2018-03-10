package com.unesco.core.repositories.plan;

import com.unesco.core.entities.Group;
import com.unesco.core.entities.Institute;
import com.unesco.core.repositories.utils.CrudPagableRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface InstituteRepository extends CrudRepository<Institute, Long>, CrudPagableRepository<Institute, Long> {
}

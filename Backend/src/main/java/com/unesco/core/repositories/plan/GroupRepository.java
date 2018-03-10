package com.unesco.core.repositories.plan;

import com.unesco.core.entities.FieldOfKnowledge;
import com.unesco.core.entities.Group;
import com.unesco.core.repositories.utils.CrudPagableRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface GroupRepository extends CrudRepository<Group, Long>, CrudPagableRepository<Group, Long> {
}

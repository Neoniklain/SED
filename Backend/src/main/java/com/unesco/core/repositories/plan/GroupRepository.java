package com.unesco.core.repositories.plan;

import com.unesco.core.entities.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface GroupRepository extends CrudRepository<Group, Long> {

   Page findAll(Pageable pageRequest);
}

package com.unesco.core.repositories.plan;

import com.unesco.core.entities.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepository extends CrudRepository<Department, Long> {
    Department findById(Long id);
    Department findByName(String name);

    Page findAll(Pageable pageRequest);
}

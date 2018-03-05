package com.unesco.core.repositories;

import com.unesco.core.entities.Department;
import com.unesco.core.entities.Professor;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepository extends CrudRepository<Department, Long> {
    Department findById(Long id);
}

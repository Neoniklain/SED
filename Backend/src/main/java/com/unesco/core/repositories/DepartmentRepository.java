package com.unesco.core.repositories;

import com.unesco.core.entities.Department;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepository extends CrudRepository<Department, Long> {
    Department findByName(String name);
}

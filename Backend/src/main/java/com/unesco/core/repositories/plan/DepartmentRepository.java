package com.unesco.core.repositories.plan;

import com.unesco.core.entities.Department;
import com.unesco.core.entities.plan.Competence;
import com.unesco.core.repositories.utils.CrudPagableRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepository extends CrudRepository<Department, Long>, CrudPagableRepository<Department, Long> {
    Department findByName(String name);
}

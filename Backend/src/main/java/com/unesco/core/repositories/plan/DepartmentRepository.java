package com.unesco.core.repositories.plan;

import com.unesco.core.entities.Department;
import com.unesco.core.entities.plan.Competence;
import com.unesco.core.repositories.utils.CrudPagableRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DepartmentRepository extends CrudRepository<Department, Long>, CrudPagableRepository<Department, Long> {
    Department findByName(String name);

    @Query("SELECT d FROM Department d where lower(d.name) LIKE CONCAT('%',lower(:filter),'%')")
    List<Department> findWithFilter(Pageable pageable, @Param("filter")  String filter);
}

package com.unesco.core.repositories.plan;

import com.unesco.core.entities.schedule.DepartmentEntity;
import com.unesco.core.repositories.utils.CrudPagableRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DepartmentRepository extends CrudRepository<DepartmentEntity, Long>, CrudPagableRepository<DepartmentEntity, Long> {
    DepartmentEntity findByName(String name);

    @Query("SELECT d FROM DepartmentEntity d where lower(d.name) LIKE CONCAT('%',lower(:filter),'%')")
    List<DepartmentEntity> findWithFilter(Pageable pageable, @Param("filter")  String filter);
}

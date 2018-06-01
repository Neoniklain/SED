package com.unesco.core.repositories.plan;

import com.unesco.core.entities.schedule.Institute;
import com.unesco.core.repositories.utils.CrudPagableRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InstituteRepository extends CrudRepository<Institute, Long>, CrudPagableRepository<Institute, Long> {

    @Query("SELECT i FROM Institute i where lower(i.name)LIKE CONCAT('%',lower(:filter),'%')")
    List<Institute> findWithFilter(Pageable pageable, @Param("filter")  String filter);

    Institute findByName(String name);

}

package com.unesco.core.repositories.plan;

import com.unesco.core.entities.schedule.InstituteEntity;
import com.unesco.core.repositories.utils.CrudPagableRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InstituteRepository extends CrudRepository<InstituteEntity, Long>, CrudPagableRepository<InstituteEntity, Long> {

    @Query("SELECT i FROM InstituteEntity i where lower(i.name)LIKE CONCAT('%',lower(:filter),'%')")
    List<InstituteEntity> findWithFilter(Pageable pageable, @Param("filter")  String filter);

    InstituteEntity findByName(String name);

}

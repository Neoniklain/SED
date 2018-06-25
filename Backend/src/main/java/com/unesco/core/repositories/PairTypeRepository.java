package com.unesco.core.repositories;

import com.unesco.core.entities.schedule.PairTypeEntity;
import com.unesco.core.repositories.utils.CrudPagableRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PairTypeRepository extends CrudRepository<PairTypeEntity, Long>, CrudPagableRepository<PairTypeEntity, Long> {
    PairTypeEntity findByType(String name);

    @Query("SELECT p FROM PairTypeEntity p where lower(p.type) LIKE CONCAT('%',lower(:filter),'%')")
    List<PairTypeEntity> findWithFilter(Pageable pageable, @Param("filter")  String filter);
}


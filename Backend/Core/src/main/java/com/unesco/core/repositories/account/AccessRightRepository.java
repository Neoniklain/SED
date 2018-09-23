package com.unesco.core.repositories.account;

import com.unesco.core.entities.account.AccessRightEntity;
import com.unesco.core.repositories.utils.CrudPagableRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccessRightRepository extends CrudRepository<AccessRightEntity, Long>, CrudPagableRepository<AccessRightEntity, Long> {

    AccessRightEntity findByName(String name);

    @Query("SELECT a FROM AccessRightEntity a where lower(a.name) LIKE CONCAT('%',lower(:filter),'%')")
    List<AccessRightEntity> findWithFilter(Pageable pageable, @Param("filter")  String filter);
}

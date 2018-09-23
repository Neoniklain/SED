package com.unesco.core.repositories.account;

import com.unesco.core.entities.account.RoleEntity;
import com.unesco.core.repositories.utils.CrudPagableRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleRepository extends CrudRepository<RoleEntity, Long>, CrudPagableRepository<RoleEntity, Long> {
    RoleEntity findByRole(String role);
    Page findAll(Pageable pageRequest);

    @Query("SELECT r FROM RoleEntity r where lower(r.role) LIKE CONCAT('%',lower(:filter),'%')")
    List<RoleEntity> findWithFilter(Pageable pageable, @Param("filter")  String filter);
}

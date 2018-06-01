package com.unesco.core.repositories.account;

import com.unesco.core.entities.account.Role;
import com.unesco.core.repositories.utils.CrudPagableRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleRepository extends CrudRepository<Role, Long>, CrudPagableRepository<Role, Long> {
    Role findByRole(String role);
    Page findAll(Pageable pageRequest);

    @Query("SELECT r FROM Role r where lower(r.role) LIKE CONCAT('%',lower(:filter),'%')")
    List<Role> findWithFilter(Pageable pageable, @Param("filter")  String filter);
}

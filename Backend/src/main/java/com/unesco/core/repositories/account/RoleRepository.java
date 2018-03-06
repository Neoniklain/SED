package com.unesco.core.repositories.account;

import com.unesco.core.entities.account.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByRole(String role);
    Page findAll(Pageable pageRequest);
}
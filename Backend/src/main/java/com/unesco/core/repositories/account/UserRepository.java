package com.unesco.core.repositories.account;

import com.unesco.core.entities.account.Role;
import com.unesco.core.entities.account.User;
import com.unesco.core.repositories.utils.CrudPagableRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>, CrudPagableRepository<User, Long> {
    Page findAll(Pageable pageRequest);
    User findByUsername(String username);
}

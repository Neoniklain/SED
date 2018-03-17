package com.unesco.core.repositories.account;

import com.unesco.core.entities.account.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
    Page findAll(Pageable pageRequest);
    User findById(long id);
}

package com.luwojtaszek.springbootjsp.repositories;

import com.luwojtaszek.springbootjsp.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}

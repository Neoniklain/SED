package com.unesco.core.repositories.account;

import com.unesco.core.entities.account.User;
import com.unesco.core.repositories.utils.CrudPagableRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long>, CrudPagableRepository<User, Long> {
    Page findAll(Pageable pageRequest);
    User findByUsername(String username);
    List<User> findAllByUserFIO(String username);

    @Query("SELECT u FROM User u WHERE " +
            "(SELECT r FROM Role r where r.role = :rolename ) member of u.roles " +
            "and u.id = :id ")
    User findByRoleAndId(@Param("rolename") String rolename, @Param("id") long id);

    @Query("SELECT u FROM User u WHERE " +
            "(SELECT r FROM Role r where r.role = :rolename ) member of u.roles ")
    List<User> findByRole(@Param("rolename") String rolename);

    @Query("SELECT u FROM User u where lower(u.userFIO) LIKE CONCAT('%',lower(:filter),'%')")
    List<User> findWithFilter(Pageable pageable, @Param("filter")  String filter);

    User findById(long id);
}

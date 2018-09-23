package com.unesco.core.repositories.account;

import com.unesco.core.entities.account.UserEntity;
import com.unesco.core.repositories.utils.CrudPagableRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<UserEntity, Long>, CrudPagableRepository<UserEntity, Long> {
    Page findAll(Pageable pageRequest);
    UserEntity findByUsername(String username);
    List<UserEntity> findAllByUserFIO(String username);

    @Query("SELECT u FROM UserEntity u WHERE " +
            "(SELECT r FROM RoleEntity r where r.role = :rolename ) member of u.roles " +
            "and u.id = :id ")
    UserEntity findByRoleAndId(@Param("rolename") String rolename, @Param("id") long id);

    @Query("SELECT u FROM UserEntity u WHERE " +
            "(SELECT r FROM RoleEntity r where r.role = :rolename ) member of u.roles ")
    List<UserEntity> findByRole(@Param("rolename") String rolename);

    @Query("SELECT u FROM UserEntity u where lower(u.userFIO) LIKE CONCAT('%',lower(:filter),'%')")
    List<UserEntity> findWithFilter(Pageable pageable, @Param("filter")  String filter);

    UserEntity findById(long id);
}

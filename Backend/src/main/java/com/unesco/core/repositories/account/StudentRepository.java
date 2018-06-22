package com.unesco.core.repositories.account;

import com.unesco.core.entities.account.StudentEntity;
import com.unesco.core.repositories.utils.CrudPagableRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends CrudRepository<StudentEntity, Long>, CrudPagableRepository<StudentEntity, Long> {
    @Override
    List<StudentEntity> findAll();

    @Query("SELECT s FROM StudentEntity s where lower(s.user.userFIO) LIKE CONCAT('%',lower(:filter),'%')")
    List<StudentEntity> findWithFilter(Pageable pageable, @Param("filter")  String filter);

    StudentEntity findByUserId(long id);

    List<StudentEntity> findAllByGroupId(long id);
}

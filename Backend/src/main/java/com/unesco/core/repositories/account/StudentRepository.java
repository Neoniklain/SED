package com.unesco.core.repositories.account;

import com.unesco.core.entities.Student;
import com.unesco.core.repositories.utils.CrudPagableRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student, Long>, CrudPagableRepository<Student, Long> {
    @Override
    List<Student> findAll();

    @Override
    @Query("SELECT s FROM Student s where s.user.id = :id")
    Student findOne(@Param("id") Long id);

    List<Student> findAllByGroupId(long id);
}

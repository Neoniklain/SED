package com.unesco.core.repositories.account;

import com.unesco.core.entities.account.Student;
import com.unesco.core.repositories.utils.CrudPagableRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student, Long>, CrudPagableRepository<Student, Long> {
    @Override
    List<Student> findAll();

    @Query("SELECT s FROM Student s where lower(s.user.userFIO) LIKE CONCAT('%',lower(:filter),'%')")
    List<Student> findWithFilter(Pageable pageable, @Param("filter")  String filter);


    Student findByUserId(long id);

    List<Student> findAllByGroupId(long id);
}

package com.unesco.core.repositories.account;

import com.unesco.core.entities.account.Professor;
import com.unesco.core.repositories.utils.CrudPagableRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProfessorRepository extends CrudRepository<Professor, Long>, CrudPagableRepository<Professor, Long> {

    @Override
    List<Professor> findAll();

    List<Professor> findAllByDepartmentId(long id);

    Professor findByUserId(long id);

    @Query("SELECT p FROM Professor p where lower(p.user.userFIO) LIKE CONCAT('%',lower(:filter),'%')")
    List<Professor> findWithFilter(Pageable pageable, @Param("filter")  String filter);

}

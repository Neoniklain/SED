package com.unesco.core.repositories.account;

import com.unesco.core.entities.account.ProfessorEntity;
import com.unesco.core.repositories.utils.CrudPagableRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProfessorRepository extends CrudRepository<ProfessorEntity, Long>, CrudPagableRepository<ProfessorEntity, Long> {

    @Override
    List<ProfessorEntity> findAll();

    List<ProfessorEntity> findAllByDepartmentId(long id);

    ProfessorEntity findByUserId(long id);

    @Query("SELECT p FROM ProfessorEntity p where lower(p.user.userFIO) LIKE CONCAT('%',lower(:filter),'%')")
    List<ProfessorEntity> findWithFilter(Pageable pageable, @Param("filter")  String filter);

}

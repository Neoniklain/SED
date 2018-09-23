package com.unesco.core.repositories.plan;

import com.unesco.core.entities.schedule.DisciplineEntity;
import com.unesco.core.repositories.utils.CrudPagableRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import java.util.List;

public interface DisciplineRepository extends CrudRepository<DisciplineEntity, Long>, CrudPagableRepository<DisciplineEntity, Long> {
    DisciplineEntity findById(long id);

    DisciplineEntity findDisciplineByName(String name);

    @Lock(LockModeType.OPTIMISTIC)
    Iterable<DisciplineEntity> findAll();

    DisciplineEntity save(DisciplineEntity s);

    DisciplineEntity findByName(String name);

    @Query("SELECT d FROM DisciplineEntity d where lower(d.name) LIKE CONCAT('%',lower(:filter),'%')")
    List<DisciplineEntity> findWithFilter(Pageable pageable, @Param("filter")  String filter);

}
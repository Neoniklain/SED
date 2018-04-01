package com.unesco.core.repositories.plan;

import com.unesco.core.entities.Department;
import com.unesco.core.entities.Discipline;
import com.unesco.core.repositories.utils.CrudPagableRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.LockModeType;
import java.util.List;

public interface DisciplineRepository extends CrudRepository<Discipline, Long>, CrudPagableRepository<Discipline, Long> {
    Discipline findById(long id);

    Discipline findDisciplineByName(String name);

    @Lock(LockModeType.OPTIMISTIC)
    Iterable<Discipline> findAll();

    Discipline save(Discipline s);

}
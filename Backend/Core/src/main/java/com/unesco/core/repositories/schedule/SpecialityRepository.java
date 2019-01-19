package com.unesco.core.repositories.schedule;

import com.unesco.core.entities.schedule.SpecialityEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpecialityRepository extends CrudRepository<SpecialityEntity, Long> {

    @Query("SELECT s FROM SpecialityEntity s where lower(s.name) LIKE CONCAT('%',lower(:filter),'%')")
    List<SpecialityEntity> findWithFilter(Pageable pageable, @Param("filter")  String filter);

    SpecialityEntity findByName(String name);
}

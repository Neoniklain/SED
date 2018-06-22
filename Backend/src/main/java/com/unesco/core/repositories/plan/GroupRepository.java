package com.unesco.core.repositories.plan;

import com.unesco.core.entities.schedule.GroupEntity;
import com.unesco.core.repositories.utils.CrudPagableRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GroupRepository extends CrudRepository<GroupEntity, Long>, CrudPagableRepository<GroupEntity, Long> {

    @Query("SELECT g FROM GroupEntity g where lower(g.name) LIKE CONCAT('%',lower(:filter),'%')")
    List<GroupEntity> findWithFilter(Pageable pageable, @Param("filter")  String filter);

    GroupEntity findByName(String name);
}

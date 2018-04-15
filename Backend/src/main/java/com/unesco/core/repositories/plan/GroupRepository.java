package com.unesco.core.repositories.plan;

import com.unesco.core.entities.FieldOfKnowledge;
import com.unesco.core.entities.Group;
import com.unesco.core.repositories.utils.CrudPagableRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GroupRepository extends CrudRepository<Group, Long>, CrudPagableRepository<Group, Long> {

    @Query("SELECT g FROM Group g where lower(g.name) LIKE CONCAT('%',lower(:filter),'%')")
    List<Group> findWithFilter(Pageable pageable, @Param("filter")  String filter);
}

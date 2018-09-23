package com.unesco.core.repositories.journal;

import com.unesco.core.entities.journal.PointTypeEntity;
import org.springframework.data.repository.CrudRepository;

public interface PointTypeRepository extends CrudRepository<PointTypeEntity, Long> {
    PointTypeEntity findByName(String name);
}

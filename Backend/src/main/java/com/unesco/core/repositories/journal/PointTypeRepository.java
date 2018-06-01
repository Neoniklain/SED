package com.unesco.core.repositories.journal;

import com.unesco.core.entities.journal.PointType;
import org.springframework.data.repository.CrudRepository;

public interface PointTypeRepository extends CrudRepository<PointType, Long> {
    PointType findByName(String name);
}

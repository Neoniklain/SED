package com.unesco.core.repositories.journal;

import com.unesco.core.entities.journal.PairEvent;
import org.springframework.data.repository.CrudRepository;

public interface LessonEventRepository extends CrudRepository<PairEvent, Long> {
    PairEvent findByTypeId(long id);
}

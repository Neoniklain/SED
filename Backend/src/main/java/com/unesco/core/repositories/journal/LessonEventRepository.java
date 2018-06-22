package com.unesco.core.repositories.journal;

import com.unesco.core.entities.journal.LessonEventEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LessonEventRepository extends CrudRepository<LessonEventEntity, Long> {
    LessonEventEntity findByTypeId(long id);

    List<LessonEventEntity> findByLessonEntityId(long lessonId);
}

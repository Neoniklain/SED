package com.unesco.core.repositories.journal;

import com.unesco.core.entities.journal.LessonEvent;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LessonEventRepository extends CrudRepository<LessonEvent, Long> {
    LessonEvent findByTypeId(long id);

    List<LessonEvent> findByLessonId(long lessonId);
}

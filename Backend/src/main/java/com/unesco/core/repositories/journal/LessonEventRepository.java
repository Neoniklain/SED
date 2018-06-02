package com.unesco.core.repositories.journal;

import com.unesco.core.entities.journal.LessonEvent;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LessonEventRepository extends CrudRepository<LessonEvent, Long> {
    LessonEvent findByTypeId(long id);

    @Query("SELECT pe from LessonEvent pe where " +
            "pe.lesson.professor.id = :professorId AND " +
            "pe.lesson.group.id = :groupId AND " +
            "pe.lesson.discipline.id = :disciplineId")
    List<LessonEvent> findByLesson(
            @Param("professorId") long professorId,
            @Param("groupId") long groupId,
            @Param("disciplineId") long disciplineId
    );
}

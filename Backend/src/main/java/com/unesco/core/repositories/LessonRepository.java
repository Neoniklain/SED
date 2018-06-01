package com.unesco.core.repositories;

import com.unesco.core.entities.schedule.Lesson;
import com.unesco.core.repositories.utils.CrudPagableRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LessonRepository extends CrudRepository<Lesson, Long>, CrudPagableRepository<Lesson, Long> {

    @Query("SELECT l FROM Lesson l")
    List<Lesson> findWithFilter(Pageable pageable, @Param("filter")  String filter);

    Lesson findByDisciplineIdAndGroupIdAndProfessorId(
            long disciplineId, long groupId, long professorId);

}

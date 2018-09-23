package com.unesco.core.repositories;

import com.unesco.core.entities.schedule.LessonEntity;
import com.unesco.core.repositories.utils.CrudPagableRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LessonRepository extends CrudRepository<LessonEntity, Long>, CrudPagableRepository<LessonEntity, Long> {

    @Query("SELECT l FROM LessonEntity l")
    List<LessonEntity> findWithFilter(Pageable pageable, @Param("filter")  String filter);

    LessonEntity findByDisciplineIdAndGroupIdAndProfessorId(
            long disciplineId, long groupId, long professorId);

    List<LessonEntity> findByProfessorId(long professorId);

}

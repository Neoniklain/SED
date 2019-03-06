package com.unesco.core.repositories.schedule;

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

    @Query("SELECT p FROM LessonEntity p where p.professor.id = :professorId and p.educationPeriod.id = :periodId")
    List<LessonEntity> findByProfessorId(@Param("professorId") long professorId, @Param("periodId") long periodId);

    @Query("SELECT p FROM LessonEntity p where p.group.id = :groupId and p.educationPeriod.id = :periodId")
    List<LessonEntity> findByGroupId(@Param("groupId") long groupId, @Param("periodId") long periodId);

}

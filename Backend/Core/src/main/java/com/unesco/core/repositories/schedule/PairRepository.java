package com.unesco.core.repositories.schedule;

import com.unesco.core.entities.schedule.PairEntity;
import com.unesco.core.repositories.utils.CrudPagableRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PairRepository extends CrudRepository<PairEntity, Long>, CrudPagableRepository<PairEntity, Long> {

    @Query("SELECT p FROM PairEntity p where p.lesson.professor.id = :professorId and p.lesson.educationPeriod.id = :periodId")
    List<PairEntity> findPairsByProfessorId(@Param("professorId") long professorId, @Param("periodId") long periodId);

    @Query("SELECT p FROM PairEntity p where p.lesson.group.id = :groupId and p.lesson.educationPeriod.id = :periodId")
    List<PairEntity> findPairsByGroupId(@Param("groupId") long groupId, @Param("periodId") long periodId);

    @Query("SELECT p FROM PairEntity p where p.lesson.professor.department.id = :departmentId and p.lesson.educationPeriod.id = :periodId")
    List<PairEntity> findPairsByDepartmentId(@Param("departmentId") long departmentId, @Param("periodId") long periodId);

    @Query("SELECT p FROM PairEntity p where p.lesson.id = :lessonId and p.lesson.educationPeriod.id = :periodId")
    List<PairEntity> findPairsByLessonId(@Param("lessonId") long lessonId, @Param("periodId") long periodId);


}

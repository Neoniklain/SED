package com.unesco.core.repositories.schedule;

import com.unesco.core.entities.schedule.StudentLessonSubgroupEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentLessonSubgroupRepository extends CrudRepository<StudentLessonSubgroupEntity, Long> {

    List<StudentLessonSubgroupEntity> findByLessonId(long lessonId);

    StudentLessonSubgroupEntity findByStudentIdAndLessonId(long studentId, long lessonId);

}


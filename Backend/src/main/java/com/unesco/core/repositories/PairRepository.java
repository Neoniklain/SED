package com.unesco.core.repositories;

import com.unesco.core.entities.schedule.Pair;
import com.unesco.core.repositories.utils.CrudPagableRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PairRepository extends CrudRepository<Pair, Long>, CrudPagableRepository<Pair, Long> {

    Pair findById(long id);

    @Query("SELECT p FROM Pair p where p.lesson.professor.id = :professorId")
    List<Pair> findPairsByProfessorId(@Param("professorId") long professorId);

    @Query("SELECT p FROM Pair p where p.lesson.group.id = :groupId")
    List<Pair> findPairsByGroupId(@Param("groupId") long groupId);

    List<Pair> findPairsByLessonId(long lessonId);

    @Query("SELECT p FROM Pair p where p.dayofweek = :dayOfWeek AND " +
            "p.pairNumber = :pairNumber AND " +
            "p.weektype = :weekType AND " +
            "p.lesson.professor.id = :professorId")
    List<Pair> findPairsByDayofweekAndPairNumberAndWeektypeAndProfessor
            (@Param("dayOfWeek") String dayOfWeek,
             @Param("pairNumber") int pairNumber,
             @Param("weekType") String weekType,
             @Param("professorId") long professor);

    List<Pair> findPairsByDayofweekAndPairNumberAndWeektypeAndRoom
            (String dayOfWeek, int pairNumber, String weekType, long room);

    @Query("SELECT p FROM Pair p where p.dayofweek = :dayOfWeek AND " +
            "p.pairNumber = :pairNumber AND " +
            "p.weektype = :weekType AND " +
            "p.lesson.group.id = :groupId")
    List<Pair> findPairsByDayofweekAndPairNumberAndWeektypeAndGroup
            (@Param("dayOfWeek") String dayOfWeek,
             @Param("pairNumber") int pairNumber,
             @Param("weekType") String weekType,
             @Param("groupId") long group);

    @Query("SELECT p FROM Pair p where p.lesson.professor.department.id = :departmentId")
    List<Pair> findPairsByDepartmentId(@Param("departmentId") long departmentId);

    @Query("SELECT p FROM Pair p")
    List<Pair> findWithFilter(Pageable pageable, @Param("filter")  String filter);
}

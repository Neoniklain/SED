package com.unesco.core.repositories.journal;

import com.unesco.core.entities.journal.Point;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface PointRepository extends CrudRepository<Point, Long> {

    @Query("SELECT p FROM Point p where p.student.user.id = :studentId AND" +
            " p.pair.id = :pairId ")
    List<Point> findByStudentIdAndPairId(@Param("studentId") long studentId,
                                           @Param("pairId") long pairId);

    @Query("SELECT p FROM Point p where p.student.user.id = :studentId AND" +
            " p.type.id = :typeId AND " +
            " p.pair.id = :pairId AND " +
            " p.date = :date")
    Point findByStudentIdAndDateAndTypeIdAndPairId(@Param("studentId") long studentId,
                                                   @Param("date") Date date,
                                                   @Param("typeId") long typeId,
                                                   @Param("pairId") long pairId);

}

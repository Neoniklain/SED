package com.unesco.core.repositories.plan;

import com.unesco.core.entities.plan.EducationPeriodEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Repository
public interface EducationPeriodRepository extends CrudRepository<EducationPeriodEntity, Long> {

    @Query("select d from EducationPeriodEntity d where d.startDate BETWEEN :from AND :to")
    EducationPeriodEntity findByStartDateBetween(@Param("from") Date startDate, @Param("to") Date endDate);

    @Modifying
    @Transactional
    @Query("select d from EducationPeriodEntity d where d.speciality.id = :specialityId and d.startDate BETWEEN :from AND :to")
    EducationPeriodEntity findBySpecialityIdAndStartDateBetween(@Param("specialityId") long specialityId, @Param("from") Date startDate, @Param("to") Date endDate);


}

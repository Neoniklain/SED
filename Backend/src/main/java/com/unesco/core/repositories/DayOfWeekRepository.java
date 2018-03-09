package com.unesco.core.repositories;

import com.unesco.core.entities.schedule.DayOfWeek;
import org.springframework.data.repository.CrudRepository;

public interface DayOfWeekRepository extends CrudRepository<DayOfWeek, Integer> {
    public DayOfWeek findDayOfWeekByDayofweek(String dayOfWeek);
}

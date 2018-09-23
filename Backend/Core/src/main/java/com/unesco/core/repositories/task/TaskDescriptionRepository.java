package com.unesco.core.repositories.task;


import com.unesco.core.entities.task.TaskDescription;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskDescriptionRepository extends CrudRepository<TaskDescription, Long> {
    TaskDescription findById(long id);
    List<TaskDescription> findAllByCreatorId(long id);
}
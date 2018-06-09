package com.unesco.core.repositories.task;


import com.unesco.core.entities.task.TaskDescriptionFile;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskDescriptionFileRepository extends CrudRepository<TaskDescriptionFile, Long> {
    List<TaskDescriptionFile> findAllByTaskDescriptionId(long id);
}
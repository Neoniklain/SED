package com.unesco.core.repositories.task;

import com.unesco.core.entities.task.TaskUserFile;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskUserFileRepository extends CrudRepository<TaskUserFile, Long> {
    List<TaskUserFile> findAllByTaskUserId(long id);
}
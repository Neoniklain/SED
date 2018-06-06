package com.unesco.core.repositories.task;


import com.unesco.core.entities.task.TaskUser;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<TaskUser, Long> {
    TaskUser findById(long id);
    Iterable<TaskUser> findByTaskDescriptionId(long id);
}
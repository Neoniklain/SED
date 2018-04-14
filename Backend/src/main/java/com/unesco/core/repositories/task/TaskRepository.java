package com.unesco.core.repositories.task;


import com.unesco.core.entities.workflow.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Long> {
    Task findById(long id);
}
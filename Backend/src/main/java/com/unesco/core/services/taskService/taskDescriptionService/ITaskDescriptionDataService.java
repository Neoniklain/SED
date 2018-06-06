package com.unesco.core.services.taskService.taskDescriptionService;

import com.unesco.core.models.TaskDescriptionModel;
import com.unesco.core.services.IDataService;

import java.util.List;

public interface ITaskDescriptionDataService extends IDataService<TaskDescriptionModel> {
    /**
     * Возвращает список описаний задач для автора
     * @param id id автора
     * @return
     */
    List<TaskDescriptionModel> GetTaskDescriptionByCreator(long id);

    /**
     * Обновление описания задачи
     * @param td новые данные описания
     */
    void UpdateTaskDescription(TaskDescriptionModel td);
}

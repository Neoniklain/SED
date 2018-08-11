package com.unesco.core.services.dataService.taskService.taskDescriptionService;

import com.unesco.core.dto.task.TaskDescriptionModel;
import com.unesco.core.services.dataService.IDataService;

import java.util.List;

public interface ITaskDescriptionDataService extends IDataService<TaskDescriptionModel> {
    /**
     * Возвращает список описаний задач для автора
     * @param id id автора
     * @return
     */
    List<TaskDescriptionModel> getTaskDescriptionByCreator(long id);

    /**
     * Обновление описания задачи
     * @param td новые данные описания
     */
    void updateTaskDescription(TaskDescriptionModel td);
}

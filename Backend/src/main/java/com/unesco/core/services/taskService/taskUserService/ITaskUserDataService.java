package com.unesco.core.services.taskService.taskUserService;

import com.unesco.core.models.TaskUserModel;
import com.unesco.core.services.IDataService;

import java.util.List;

public interface ITaskUserDataService extends IDataService<TaskUserModel> {
    /**
     * Возвращает список задач для указанного описания задачи
     * @param id id описания задачи
     * @return
     */
    List<TaskUserModel> GetTaskUserByTaskDescription(long id);

    /**
     * Обновление конкретной реализации задачи
     * @param tu новые данные реализации
     */
    void UpdateTaskUser(TaskUserModel tu);
}

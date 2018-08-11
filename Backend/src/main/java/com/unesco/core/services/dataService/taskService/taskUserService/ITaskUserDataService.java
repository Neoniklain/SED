package com.unesco.core.services.dataService.taskService.taskUserService;

import com.unesco.core.dto.task.TaskUserModel;
import com.unesco.core.services.dataService.IDataService;

import java.util.List;

public interface ITaskUserDataService extends IDataService<TaskUserModel> {
    /**
     * Возвращает список задач для указанного описания задачи
     * @param id id описания задачи
     * @return
     */
    List<TaskUserModel> getTaskUserByTaskDescription(long id);

    /**
     * Обновление конкретной реализации задачи
     * @param tu новые данные реализации
     */
    void updateTaskUser(TaskUserModel tu);

    /**
     * Возвращает список реализаций задач, в которых пользователь является исполнителем.
     * @param id id пользователя
     */
    List<TaskUserModel> getTaskUsersByExecutor(long id);
}

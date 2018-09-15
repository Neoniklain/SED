package com.unesco.core.services.dataService.taskService.taskUserService;

import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.task.TaskUserDTO;
import com.unesco.core.services.dataService.IDataService;

import java.util.List;

public interface ITaskUserDataService extends IDataService<TaskUserDTO> {
    /**
     * Обновление данных задачи
     * @param newTUModel Новая модель задачи
     * @return Статус выполнения
     */
    ResponseStatusDTO<TaskUserDTO> update(TaskUserDTO newTUModel);

    /**
     * Изменение статуса задачи
     * @param TUId Id задачи для пользователя
     * @param statusId Id нового статуса
     * @return
     */
    ResponseStatusDTO<TaskUserDTO> changeStatus(long TUId, int statusId);

    // ЗАМЕНИТЬ НА ФИЛЬТРАЦИЮ!!!!!!!!
    // Либо вообще убрать, и фильтровать в менеджере
    /**
     * Возвращает список реализаций задач, в которых пользователь является исполнителем.
     * @param id id пользователя
     */
    List<TaskUserDTO> getByExecutor(long id);
}

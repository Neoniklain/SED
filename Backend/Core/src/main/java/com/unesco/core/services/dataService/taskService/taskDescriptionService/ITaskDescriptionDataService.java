package com.unesco.core.services.dataService.taskService.taskDescriptionService;

import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.task.TaskDescriptionDTO;
import com.unesco.core.services.dataService.IDataService;

import java.util.List;

public interface ITaskDescriptionDataService extends IDataService<TaskDescriptionDTO> {

    // ЗАМЕНИТЬ НА ФИЛЬТРАЦИЮ!!!!!!!!
    // Либо вообще убрать, и фильтровать в менеджере
    /**
     * Возвращает список описаний задач для автора
     * @param id id автора
     * @return
     */
    List<TaskDescriptionDTO> getByCreator(long id);
    /**
     * Обновление данных задачи
     * @param newTDModel Новая модель задачи
     * @return Статус выполнения
     */
    ResponseStatusDTO<TaskDescriptionDTO> update(TaskDescriptionDTO newTDModel);

    /**
     * Изменение статуса задачи
     * @param TDId Id задачи
     * @param statusId Id нового статуса
     * @return Статус выполнения
     */
    ResponseStatusDTO<TaskDescriptionDTO> changeStatus(long TDId, int statusId);
}

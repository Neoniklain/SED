package com.unesco.core.managers.task.interfaces;

import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.task.TaskDescriptionDTO;
import com.unesco.core.dto.task.TaskUserDTO;

import java.util.List;

public interface ITaskService {
    // ------------------------------------------------------------
    // ----- методы для описания задач (for TaskDescription) ------
    // --------------------- Основные функции ---------------------
    // ------------------------------------------------------------

    /**
     * Создаёт задачу и её реализации.
     * @param td модель новой задачи
     * @return Статус выполнения
     */
    ResponseStatusDTO<TaskDescriptionDTO> createNewTask(TaskDescriptionDTO td);

    /**
     * Удаляет описание задачи и её реализации.
     * @param id id существующей задачи
     * @return Статус выполнения
     */
    ResponseStatusDTO<TaskDescriptionDTO> deleteTaskDesc(long id);

    /**
     * Обновляет существующее описание задачи
     * @param td новая модель задачи
     * @return Статус выполнения
     */
    ResponseStatusDTO<TaskDescriptionDTO> updateTaskDesc(TaskDescriptionDTO td);

    /**
     * Возвращает список всех описаний задач
     */
    List<TaskDescriptionDTO> getAllTaskDescs(boolean isIncludeTU);

    // ------------------------------------------------------------
    // ----------------- Вспомогательные функции ------------------
    // ------------------------------------------------------------

    /**
     * Возвращает описание задачи
     * @param id id задачи
     */
    TaskDescriptionDTO getTaskDescById(long id, boolean isIncludeTU);

    /**
     * Возвращает список задач (с его реализацией), в которых пользователь является исполнителем.
     * @param id id пользователя
     */
    List<TaskDescriptionDTO> getTaskDescsByExecutor(long id);

    /**
     * Возвращает список задач, которые создал пользователь.
     * @param id id пользователя
     */
    List<TaskDescriptionDTO> getTaskDescsByCreator(long id, boolean isIncludeTU);

    /**
     * Изменить статус
     * @param td_id id задачи
     * @param status_id id статуса
     * @return Статус выполнения
     */
    ResponseStatusDTO<TaskDescriptionDTO> changeStatusTaskDesc(long td_id, int status_id);

    // ------------------------------------------------------------
    // --------- методы для "ответа" задач (for TaskUser) ---------
    // --------------------- Основные функции ---------------------
    // ------------------------------------------------------------
    /**
     * Удаляет реализацию задачи.
     * @param id id реализации задачи
     * @return Статус выполнения
     */
    ResponseStatusDTO<TaskUserDTO> deleteTaskUser(long id);

    /**
     * Возвращает модель реализации задачи для указанного id.
     * @param id id реализации задачи
     */
    TaskUserDTO getTaskUserById(long id);

    /**
     * Обновляет реализацию.
     * @param tu новая модель реализации
     * @return Статус выполнения
     */
    ResponseStatusDTO<TaskUserDTO> updateTaskUser(TaskUserDTO tu);

    /**
     * Возвращает список реализаций для указанного описания задачи.
     * @param id id описания задачи
     */
    List<TaskUserDTO> getTaskUsersByTDID(long id);

    /**
     * Изменить статус
     * @param tu_id id задачи пользователя
     * @param status_id id статуса
     * @return Статус выполнения
     */
    ResponseStatusDTO<TaskUserDTO> changeStatusTaskUser(long tu_id, int status_id);
}

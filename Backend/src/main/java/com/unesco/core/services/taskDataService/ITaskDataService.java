package com.unesco.core.services.taskDataService;

import com.unesco.core.entities.workflow.Task;
import com.unesco.core.entities.workflow.TaskDescription;
import com.unesco.core.models.TaskDescriptionModel;
import com.unesco.core.models.TaskModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ITaskDataService {
   /**
    * Возвращает список всех описаний задач
    */
   List<TaskDescriptionModel> getAllTaskDescription();
   /**
    * Возвращает описание задачи для указанной реализации.
    * @param id id реализации задачи
    */
   TaskDescriptionModel getTaskDescriptionBySubTasks(long id);
   /**
    * Возвращает описания задач для указанного создателя.
    * @param id id автора задачи
    */
   List<TaskDescriptionModel> getTaskDescriptionByCreator(long id);
   /**
    * Возвращает список реализаций для указанного описания задачи.
    * @param id id описания задачи
    */
   List<TaskModel> getSubTasksForTaskDescription(long id);
   /**
    * Создаёт новое описание задачи и её реализации.
    * @param td модель новой задачи
    */
   void createNewTaskDescription(TaskDescriptionModel td);
   /**
    * Обновляет существующее описание задачи и её реализации.
    * @param td новая модель существующей задачи
    */
   void updateTaskDescription(TaskDescriptionModel td);
   /**
    * Удаляет описание задачи и её реализации.
    * @param id id существующей задачи
    */
   void deleteTaskDescription(long id);
   /**
    * Сохраняет ответ на задачу от пользователя.
    * @param item модель ответа на задачи
    */
   void answerTask(TaskModel item);
   /**
    * Возвращает модель описания задачи для указанного id.
    * @param id id существующей задачи
    */
   TaskDescriptionModel getTaskDescriptionById(long id);
   /**
    * Переводит Entity в Model.
    * @param tds Список Entity
    */
   List<TaskDescriptionModel> EntityToModel(List<TaskDescription> tds);
   /**
    * Переводит Entity в Model.
    * @param tds Список Entity
    */
   List<TaskDescriptionModel> EntityToModel(Iterable<TaskDescription> tds);

}

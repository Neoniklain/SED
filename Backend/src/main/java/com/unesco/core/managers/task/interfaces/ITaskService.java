package com.unesco.core.managers.task.interfaces;

import com.unesco.core.models.task.TaskDescriptionModel;
import com.unesco.core.models.task.TaskUserModel;

import java.util.List;

public interface ITaskService {

   /**
    * Возвращает список всех описаний задач
    */
   List<TaskDescriptionModel> getAllTaskDescription();

   /**
    * Возвращает описание задачи для указанной реализации.
    * @param id id реализации задачи
    */
   TaskDescriptionModel getTaskDescriptionByTaskUser(long id);

   /**
    * Возвращает описания задач для указанного создателя.
    * @param id id автора задачи
    */
   List<TaskDescriptionModel> getTaskDescriptionByCreator(long id);

   /**
    * Возвращает список реализаций для указанного описания задачи.
    * @param id id описания задачи
    */
   List<TaskUserModel> getTaskUsersForTaskDescription(long id);

   /**
    * Создаёт новое описание задачи и её реализации.
    * @param td модель новой задачи
    * @return Возвращает ДТО созданного объекта.
    */
   TaskDescriptionModel createNewTaskDescription(TaskDescriptionModel td);

   /**
    * Обновляет существующее описание задачи и её реализации.
    * @param td новая модель существующей задачи
    */
   void updateTaskDescription(TaskDescriptionModel td);

   /**
    * Обновляет существующую реализацю задачи.
    * @param td новая модель реализации задачи
    */
   void updateTaskUser(TaskUserModel tu);

   /**
    * Удаляет описание задачи и её реализации.
    * @param id id существующей задачи
    */
   void deleteTaskDescription(long id);

   /**
    * Сохраняет ответ на задачу от пользователя.
    * @param item модель ответа на задачи
    */
   void changeStatusTaskUser(TaskUserModel item);

   /**
    * Возвращает модель описания задачи для указанного id.
    * @param id id существующей задачи
    */
   TaskDescriptionModel getTaskDescriptionById(long id);

   /**
    * Возвращает список реализаций задач, в которых пользователь является исполнителем.
    * @param id id пользователя
    */
   List<TaskUserModel> getTaskUsersByExecutor(long id);


   /**
    * Возвращает модель реализации задачи для указанного id.
    * @param id id реализации задачи
    */
   TaskUserModel getTaskUserById(long id);
}

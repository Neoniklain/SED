package com.unesco.core.services.taskDataService;

import com.fasterxml.jackson.databind.util.ArrayIterator;
import com.unesco.core.entities.workflow.Task;
import com.unesco.core.entities.workflow.TaskDescription;
import com.unesco.core.models.TaskDescriptionModel;
import com.unesco.core.models.TaskModel;
import com.unesco.core.models.account.UserModel;
import com.unesco.core.repositories.account.UserRepository;
import com.unesco.core.repositories.task.TaskDescriptionRepository;
import com.unesco.core.repositories.task.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TaskDataService implements ITaskDataService
{
   //Закончил на реализации интерфейса. Не проверял. Короче всё доделать.
   @Autowired
   TaskDescriptionRepository _TaskDescriptionRepository;
   @Autowired
   UserRepository _UserRepository;
   @Autowired
   TaskRepository _TaskRepository;

   TaskStatusList _Statuses;

   @Override
   public List<TaskDescriptionModel> getAllTaskDescription() {
      return EntityToModel(_TaskDescriptionRepository.findAll());
   }

   @Override
   public TaskDescriptionModel getTaskDescriptionBySubTasks(long id) {
      return new TaskDescriptionModel(_TaskDescriptionRepository.findBySubTasks(_TaskRepository.findById(id)));
   }

   @Override
   public List<TaskDescriptionModel> getTaskDescriptionByCreator(long id) {
      return EntityToModel(_TaskDescriptionRepository.findByCreator(id));
   }

   @Override
   public List<TaskModel> getSubTasksForTaskDescription(long id) {
      TaskDescriptionModel temp = new TaskDescriptionModel(_TaskDescriptionRepository.findById(id));
      return temp.getSubTasks();
   }

   @Override
   public void createNewTaskDescription(TaskDescriptionModel td){
      TaskDescription res = new TaskDescription();
      res.setCreator(_UserRepository.findById(td.getCreator().getId()));
      res.setName(td.getName());
      res.setDescription(td.getDescription());
      Set<Task> col = new HashSet<>();
      List<Task> subTasks = new ArrayList<>();
      for (UserModel user: td.getUsers()) {
          Task temp = new Task();
          temp.setResponse("");
          temp.setStatus(_Statuses.Processed.toString());
          temp.setTaskDescription(res);
          temp.setExecutor(_UserRepository.findById(user.getId()));
          subTasks.add(temp);
          col.add(temp);
         //col.add(_TaskRepository.findById(task.getId()));
      }
      res.setSubTasks((Set<Task>)col);

      if(!res.getSubTasks().isEmpty())
      {
         _TaskDescriptionRepository.save(res);
         _TaskRepository.save(subTasks);
         System.out.println("Задача добавлена.");
      }
      else
      {
         System.out.println("Невозможно создать задачу. Не выбраны пользователи.");
      }
   }

   @Override
   public void updateTaskDescription(TaskDescriptionModel td) {
      TaskDescription res = _TaskDescriptionRepository.findById(td.getId());
      res.setName(td.getName());
      res.setDescription(td.getDescription());
      Set<Task> col = new HashSet<>();
      for (TaskModel task: td.getSubTasks()) {
         col.add(_TaskRepository.findById(task.getId()));
      }
      res.setSubTasks(col);
      //_TaskDescriptionRepository.save(res);
      System.out.println("Задача обновлена!.");
   }

   @Override
   public void deleteTaskDescription(long id) {
       TaskDescription temp = _TaskDescriptionRepository.findById(id);
       for (Task item : temp.getSubTasks()) {
           _TaskRepository.delete(item.getId());
       }
       _TaskDescriptionRepository.delete(temp.getId());
   }

   @Override
   public TaskDescriptionModel getTaskDescriptionById(long id) {
      return new TaskDescriptionModel(_TaskDescriptionRepository.findById(id));
   }

   @Override
   public List<TaskDescriptionModel> EntityToModel(List<TaskDescription> tds) {
      List<TaskDescriptionModel> res = new ArrayList<>();
      for (TaskDescription task: tds) {
         res.add(new TaskDescriptionModel(task));
      }
      return res;
   }

   @Override
   public List<TaskDescriptionModel> EntityToModel(Iterable<TaskDescription> tds) {
      List<TaskDescriptionModel> res = new ArrayList<>();
      for (TaskDescription task: tds) {
         res.add(new TaskDescriptionModel(task));
      }
      return res;
   }
}

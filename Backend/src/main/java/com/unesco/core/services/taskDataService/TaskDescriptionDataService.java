package com.unesco.core.services.taskDataService;

import com.unesco.core.entities.account.User;
import com.unesco.core.entities.workflow.Task;
import com.unesco.core.entities.workflow.TaskDescription;
import com.unesco.core.models.TaskDescriptionModel;
import com.unesco.core.models.TaskModel;
import com.unesco.core.models.account.UserModel;
import com.unesco.core.repositories.account.UserRepository;
import com.unesco.core.repositories.issue.TaskDescriptionRepository;
import com.unesco.core.repositories.issue.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TaskDescriptionDataService implements ITaskDescriptionDataService
{
   //Закончил на реализации интерфейса. Не проверял. Короче всё доделать.
   @Autowired
   TaskDescriptionRepository _TaskDescriptionRepository;
   @Autowired
   UserRepository _UserRepository;
   @Autowired
   TaskRepository _TaskRepository;

   @Override
   public List<TaskDescriptionModel> getAllTaskDescription() {
      return EntityToModel(_TaskDescriptionRepository.findAll());
   }

   @Override
   public List<TaskDescriptionModel> getTaskDescriptionBySubTasks(long id) {
      return EntityToModel(_TaskDescriptionRepository.findBySubTasks(_TaskRepository.findById(id)));
   }

   @Override
   public List<TaskDescriptionModel> getTaskDescriptionByCreator(long id) {
      return EntityToModel(_TaskDescriptionRepository.findByCreator(id));
   }

   @Override
   public void createNewTaskDescription(TaskDescriptionModel td){
      TaskDescription res = new TaskDescription();
      res.setCreator(_UserRepository.findById(td.getCreator().getId()));
      res.setName(td.getName());
      res.setDescription(td.getDescription());
      Set<Task> col = new HashSet<>();
      for (TaskModel task: td.getSubTasks()) {
         col.add(_TaskRepository.findById(task.getId()));
      }
      res.setSubTasks((Set<Task>)col);

      if(!res.getSubTasks().isEmpty())
         //_TaskDescriptionRepository.save(res);
         System.out.println("Задача добавлена.");
      else
         System.out.println("Невозможно создать задачу. Не выбраны пользователи.");
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
      _TaskDescriptionRepository.delete(id);
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

package com.unesco.core.services.taskService;

import com.unesco.core.entities.workflow.Task;
import com.unesco.core.entities.workflow.TaskDescription;
import com.unesco.core.models.TaskDescriptionModel;
import com.unesco.core.models.TaskModel;
import com.unesco.core.models.account.UserDTO;
import com.unesco.core.repositories.account.UserRepository;
import com.unesco.core.repositories.task.TaskDescriptionRepository;
import com.unesco.core.repositories.task.TaskRepository;
import com.unesco.core.services.mapperService.MapperService;
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
   @Autowired
   MapperService _MapperService;

   TaskStatusList _Statuses;

   @Override
   public List<TaskDescriptionModel> getAllTaskDescription() {
      return EntityToModel(_TaskDescriptionRepository.findAll());
   }

   @Override
   public TaskDescriptionModel getTaskDescriptionBySubTasks(long id) {
      return (TaskDescriptionModel) _MapperService.toModel(_TaskDescriptionRepository.findBySubTasks(_TaskRepository.findById(id)));
   }

   @Override
   public List<TaskDescriptionModel> getTaskDescriptionByCreator(long id) {
      return EntityToModel(_TaskDescriptionRepository.findByCreator(id));
   }

   @Override
   public List<TaskModel> getSubTasksForTaskDescription(long id) {
      TaskDescriptionModel temp = (TaskDescriptionModel) _MapperService.toModel(_TaskDescriptionRepository.findById(id));
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
      for (UserDTO user: td.getUsers()) {
          Task temp = new Task();
          temp.setResponse("");
          temp.setStatus(_Statuses.Processed);
          temp.setTaskDescription(res);
          temp.setExecutor(_UserRepository.findById(user.getId()));
          subTasks.add(temp);
          col.add(temp);
         //col.add(_TaskRepository.findById(task.getId()));
      }
      res.setSubTasks((List<Task>)subTasks);

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
      List<Task> col = new ArrayList<>();
      for (TaskModel task: td.getSubTasks()) {
         col.add(_TaskRepository.findById(task.getId()));
      }
      res.setSubTasks(col);
      _TaskDescriptionRepository.save(res);
      System.out.println("Задача обновлена!");
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
   public void answerTask(TaskModel item) {
      Task res = _TaskRepository.findById(item.getId());
      //res.setStatus(_Statuses.SentToRevision.toString());
      res.setStatus(item.getStatus());
      res.setResponse(item.getResponse());
      _TaskRepository.save(res);
   }

   @Override
   public TaskDescriptionModel getTaskDescriptionById(long id) {
      return (TaskDescriptionModel) _MapperService.toModel(_TaskDescriptionRepository.findById(id));
   }

   @Override
   public List<TaskDescriptionModel> EntityToModel(List<TaskDescription> tds) {
      List<TaskDescriptionModel> res = new ArrayList<>();
      for (TaskDescription task: tds) {
         res.add((TaskDescriptionModel) _MapperService.toModel(task));
      }
      return res;
   }

   @Override
   public List<TaskDescriptionModel> EntityToModel(Iterable<TaskDescription> tds) {
      List<TaskDescriptionModel> res = new ArrayList<>();
      for (TaskDescription task: tds) {
         res.add((TaskDescriptionModel) _MapperService.toModel(task));
      }
      return res;
   }
}

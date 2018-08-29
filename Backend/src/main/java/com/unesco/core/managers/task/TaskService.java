package com.unesco.core.managers.task;

import com.unesco.core.dto.account.UserDTO;
import com.unesco.core.dto.enums.TaskStatusType;
import com.unesco.core.dto.enums.TaskType;
import com.unesco.core.dto.task.TaskDescriptionModel;
import com.unesco.core.dto.task.TaskUserModel;
import com.unesco.core.managers.task.interfaces.ITaskService;
import com.unesco.core.services.dataService.account.userService.IUserDataService;
import com.unesco.core.services.mapperService.MapperService;
import com.unesco.core.services.dataService.taskService.taskDescriptionService.ITaskDescriptionDataService;
import com.unesco.core.services.dataService.taskService.taskUserService.ITaskUserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService implements ITaskService
{
   @Autowired
   MapperService _mapperService;
   @Autowired
   ITaskDescriptionDataService _taskDescriptionDataService;
   @Autowired
   ITaskUserDataService _taskUserDataService;
   @Autowired
   IUserDataService _userDataService;
   @Autowired
   IUserDataService _userService;

   @Override
   public List<TaskDescriptionModel> getAllTaskDescription() {
      return _taskDescriptionDataService.getAll();
   }

   @Override
   public TaskDescriptionModel getTaskDescriptionByTaskUser(long id) {
      TaskUserModel temp = _taskUserDataService.get(id);
      return (_taskDescriptionDataService.get(temp.getTaskDescriptionId()));
   }

   @Override
   public List<TaskDescriptionModel> getTaskDescriptionByCreator(long id) {
      return _taskDescriptionDataService.getTaskDescriptionByCreator(id);
   }

   @Override
   public List<TaskUserModel> getTaskUsersForTaskDescription(long id) {
      return _taskUserDataService.getTaskUserByTaskDescription(id);
   }

   @Override
   public TaskDescriptionModel createNewTaskDescription(TaskDescriptionModel td){
      if(td.getUsers().size() == 0)
         return null;
      TaskStatusType taskStatusType;
      if(td.getType() == TaskType.Info.ordinal())
      {
         taskStatusType = TaskStatusType.Completed;
      }
      else
      {
         taskStatusType = TaskStatusType.Processed;
      }
      TaskDescriptionModel saved = _taskDescriptionDataService.save(td).getData();
      List<TaskUserModel> ltu = new ArrayList<>();
      for(UserDTO user:td.getUsers()){
         if(user.getId() != td.getCreator().getId()){
            TaskUserModel newTUM = new TaskUserModel();
            newTUM.setResponse("");
            newTUM.setStatus(taskStatusType.ordinal());
            newTUM.setExecutor(user);
            newTUM.setStatusName(taskStatusType.name());
            newTUM.setTaskDescriptionId(saved.getId());
            ltu.add(_taskUserDataService.save(newTUM).getData());
         }
         else{
            System.out.println("Нельзя назначить задачу самому себе.");
         }
      }
      saved.setTaskUsers(ltu);
      System.out.println("Задача добавлена.");
      return saved;
   }

   @Override
   public void updateTaskDescription(TaskDescriptionModel td) {
      _taskDescriptionDataService.updateTaskDescription(td);
      System.out.println("Задача обновлена!");
   }

   @Override
   public void updateTaskUser(TaskUserModel tu) {
      _taskUserDataService.updateTaskUser(tu);
   }

   @Override
   public void deleteTaskDescription(long id) {
       TaskDescriptionModel task = _taskDescriptionDataService.get(id);
       List<TaskUserModel> subTasks = _taskUserDataService.getTaskUserByTaskDescription(id);
       for (TaskUserModel item : subTasks) {
          _taskUserDataService.delete(item.getId());
       }
      _taskDescriptionDataService.delete(task.getId());
   }

   @Override
   public void changeStatusTaskUser(long tu_id, int status_id) {
      TaskUserModel TU = _taskUserDataService.get(tu_id);
      TU.setStatus(status_id);
      _taskUserDataService.updateTaskUser(TU);
   }

   @Override
   public void answerTaskUser(TaskUserModel item) {
      _taskUserDataService.updateTaskUser(item);
      List<TaskUserModel> tums = _taskUserDataService.getTaskUserByTaskDescription(item.getTaskDescriptionId());
      int closedCount = 0;
      int totalCount = 0;
      for (TaskUserModel tum: tums) {
         totalCount++;
         if(tum.getStatus()== TaskStatusType.Completed.ordinal()){
            closedCount++;
         }
         if(tum.getStatus()==TaskStatusType.Denied.ordinal()){
            closedCount++;
         }
      }
      if(totalCount == closedCount){
         TaskDescriptionModel forUp = _taskDescriptionDataService.get(item.getTaskDescriptionId());
         forUp.setStatus(TaskStatusType.Completed.ordinal());
         _taskDescriptionDataService.updateTaskDescription(forUp);
      }
   }

   @Override
   public TaskDescriptionModel getTaskDescriptionById(long id) {
      return _taskDescriptionDataService.get(id);
   }

   @Override
   public List<TaskUserModel> getTaskUsersByExecutor(long id) {
      return _taskUserDataService.getTaskUsersByExecutor(id);
   }

   @Override
   public TaskUserModel getTaskUserById(long id) {
      return _taskUserDataService.get(id);
   }
}

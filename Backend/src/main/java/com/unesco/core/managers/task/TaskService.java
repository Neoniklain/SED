package com.unesco.core.managers.task;

import com.unesco.core.managers.task.interfaces.ITaskService;
import com.unesco.core.models.task.TaskDescriptionModel;
import com.unesco.core.models.task.TaskUserModel;
import com.unesco.core.models.account.UserModel;
import com.unesco.core.models.enums.TaskStatusType;
import com.unesco.core.services.account.userService.IUserDataService;
import com.unesco.core.services.file.fileByteCodeService.IFileByteCodeService;
import com.unesco.core.services.file.fileDescriptionService.IFileDescriptionService;
import com.unesco.core.services.mapperService.MapperService;
import com.unesco.core.services.taskService.taskDescriptionService.ITaskDescriptionDataService;
import com.unesco.core.services.taskService.taskUserService.ITaskUserDataService;
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
   @Autowired
   IFileDescriptionService _fileDescriptionService;
   @Autowired
   IFileByteCodeService _fileByteCodeService;

   @Override
   public List<TaskDescriptionModel> getAllTaskDescription() {
      return _taskDescriptionDataService.GetAll();
   }

   @Override
   public TaskDescriptionModel getTaskDescriptionByTaskUser(long id) {
      TaskUserModel temp = _taskUserDataService.Get(id);
      return (_taskDescriptionDataService.Get(temp.getTaskDescriptionId()));
   }

   @Override
   public List<TaskDescriptionModel> getTaskDescriptionByCreator(long id) {
      return _taskDescriptionDataService.GetTaskDescriptionByCreator(id);
   }

   @Override
   public List<TaskUserModel> getTaskUsersForTaskDescription(long id) {
      return _taskUserDataService.GetTaskUserByTaskDescription(id);
   }

   @Override
   public TaskDescriptionModel createNewTaskDescription(TaskDescriptionModel td){
      TaskDescriptionModel saved = _taskDescriptionDataService.Save(td);
      List<TaskUserModel> ltu = new ArrayList<>();
      for(UserModel user:td.getUsers()){
         TaskUserModel newTUM = new TaskUserModel();
         newTUM.setResponse("");
         newTUM.setStatus(TaskStatusType.Processed.ordinal());
         newTUM.setExecutor(user);
         newTUM.setStatusName(TaskStatusType.Processed.name());
         newTUM.setTaskDescriptionId(saved.getId());
         ltu.add(_taskUserDataService.Save(newTUM));
      }
      saved.setTaskUsers(ltu);
      System.out.println("Задача добавлена.");
      return saved;
   }

   @Override
   public void updateTaskDescription(TaskDescriptionModel td) {
      _taskDescriptionDataService.UpdateTaskDescription(td);
      System.out.println("Задача обновлена!");
   }

   @Override
   public void updateTaskUser(TaskUserModel tu) {
      _taskUserDataService.UpdateTaskUser(tu);
   }

   @Override
   public void deleteTaskDescription(long id) {
       TaskDescriptionModel task = _taskDescriptionDataService.Get(id);
       List<TaskUserModel> subTasks = _taskUserDataService.GetTaskUserByTaskDescription(id);
       for (TaskUserModel item : subTasks) {
          _taskUserDataService.Delete(item.getId());
       }
      _taskDescriptionDataService.Delete(task.getId());
   }

   @Override
   public void changeStatusTaskUser(TaskUserModel item) {
      _taskUserDataService.UpdateTaskUser(item);
      List<TaskUserModel> tums = _taskUserDataService.GetTaskUserByTaskDescription(item.getTaskDescriptionId());
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
         TaskDescriptionModel forUp = _taskDescriptionDataService.Get(item.getTaskDescriptionId());
         forUp.setStatus(TaskStatusType.Completed.ordinal());
         _taskDescriptionDataService.UpdateTaskDescription(forUp);
      }
   }

   @Override
   public TaskDescriptionModel getTaskDescriptionById(long id) {
      return _taskDescriptionDataService.Get(id);
   }

   @Override
   public List<TaskUserModel> getTaskUsersByExecutor(long id) {
      return _taskUserDataService.getTaskUsersByExecutor(id);
   }

   @Override
   public TaskUserModel getTaskUserById(long id) {
      return _taskUserDataService.Get(id);
   }
}

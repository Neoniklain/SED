package com.unesco.core.services.taskService.taskUserService;

import com.unesco.core.entities.file.FileDescription;
import com.unesco.core.entities.task.TaskUser;
import com.unesco.core.models.file.FileDescriptionModel;
import com.unesco.core.models.task.TaskUserModel;
import com.unesco.core.repositories.task.TaskRepository;
import com.unesco.core.services.mapperService.IMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TaskUserDataService implements ITaskUserDataService
{
   @Autowired
   TaskRepository _taskRepository;
   @Autowired
   private IMapperService _mapperService;

   @Override
   public TaskUserModel Save(TaskUserModel taskModel) {
      TaskUser forSave = (TaskUser) _mapperService.toEntity(taskModel);
      return (TaskUserModel) _mapperService.toModel(_taskRepository.save(forSave));
   }

   @Override
   public void Delete(long id) {
      _taskRepository.delete(id);
   }

   @Override
   public TaskUserModel Get(long id) {
      return (TaskUserModel) _mapperService.toModel(_taskRepository.findById(id));
   }

   @Override
   public List<TaskUserModel> GetAll() {
      List<TaskUserModel> result = new ArrayList<>();
      Iterable<TaskUser> entities = _taskRepository.findAll();
      for (TaskUser item:entities) {
         result.add((TaskUserModel) _mapperService.toModel(item));
      }
      return result;
   }

   @Override
   public List<TaskUserModel> GetTaskUserByTaskDescription(long id) {
      Iterable<TaskUser> tasks = _taskRepository.findByTaskDescriptionId(id);
      List<TaskUserModel> result = new ArrayList<>();
      for (TaskUser item : tasks) {
         result.add((TaskUserModel)_mapperService.toModel(item));
      }
      return result;
   }

   @Override
   public void UpdateTaskUser(TaskUserModel tu) {
      TaskUser up = _taskRepository.findById(tu.getId());
      up.setStatus(tu.getStatus());
      up.setResponse(tu.getResponse());
      Set<FileDescription> files = new HashSet<>();
      for(FileDescriptionModel file: tu.getFiles()){
         files.add((FileDescription) _mapperService.toEntity(file));
      }
      up.setFiles(files);
      _taskRepository.save(up);
   }

   @Override
   public List<TaskUserModel> getTaskUsersByExecutor(long id) {
      Iterable<TaskUser> tasks = _taskRepository.findByExecutorId(id);
      List<TaskUserModel> result = new ArrayList<>();
      for (TaskUser item : tasks) {
         result.add((TaskUserModel)_mapperService.toModel(item));
      }
      return result;
   }
}

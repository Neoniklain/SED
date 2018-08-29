package com.unesco.core.services.dataService.taskService.taskUserService;

import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.enums.StatusTypes;
import com.unesco.core.dto.file.FileDescriptionModel;
import com.unesco.core.dto.task.TaskUserModel;
import com.unesco.core.entities.file.FileDescription;
import com.unesco.core.entities.task.TaskUser;
import com.unesco.core.repositories.task.TaskRepository;
import com.unesco.core.services.mapperService.IMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TaskUserDataService implements ITaskUserDataService
{
   @Autowired
   private TaskRepository _taskRepository;
   @Autowired
   private IMapperService _mapperService;

   @Override
   public ResponseStatusDTO<TaskUserModel> save(TaskUserModel taskModel) {
      TaskUser entity = (TaskUser) _mapperService.toEntity(taskModel);
      ResponseStatusDTO<TaskUserModel> result = new ResponseStatusDTO<>(StatusTypes.OK);
      try {
         entity = _taskRepository.save(entity);
      } catch (Exception e) {
         result.setStatus(StatusTypes.ERROR);
         result.addErrors(e.getMessage());
         return result;
      }
      result.setData((TaskUserModel) _mapperService.toDto(entity));
      return result;
   }

   @Override
   public ResponseStatusDTO<TaskUserModel> delete(long id) {
      ResponseStatusDTO<TaskUserModel> result = new ResponseStatusDTO<>(StatusTypes.OK);
      try {
         _taskRepository.delete(id);
      } catch (Exception e) {
         result.setStatus(StatusTypes.ERROR);
         if(e instanceof DataIntegrityViolationException)
            result.addErrors("Удаление не удалось. У объекта есть зависимости.");
         result.addErrors("Удаление не удалось");
         return result;
      }
      return result;
   }

   @Override
   public TaskUserModel get(long id) {
      return (TaskUserModel) _mapperService.toDto(_taskRepository.findById(id));
   }

   @Override
   public List<TaskUserModel> getAll() {
      List<TaskUserModel> result = new ArrayList<>();
      Iterable<TaskUser> entities = _taskRepository.findAll();
      for (TaskUser item:entities) {
         result.add((TaskUserModel) _mapperService.toDto(item));
      }
      return result;
   }

   @Override
   public List<TaskUserModel> getTaskUserByTaskDescription(long id) {
      Iterable<TaskUser> tasks = _taskRepository.findByTaskDescriptionId(id);
      List<TaskUserModel> result = new ArrayList<>();
      for (TaskUser item : tasks) {
         result.add((TaskUserModel)_mapperService.toDto(item));
      }
      return result;
   }

   @Override
   public void updateTaskUser(TaskUserModel tu) {
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
         result.add((TaskUserModel)_mapperService.toDto(item));
      }
      return result;
   }
}

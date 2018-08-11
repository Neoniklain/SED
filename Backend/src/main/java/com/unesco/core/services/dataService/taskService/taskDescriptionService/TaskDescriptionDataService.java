package com.unesco.core.services.dataService.taskService.taskDescriptionService;

import com.unesco.core.entities.file.FileDescription;
import com.unesco.core.entities.task.TaskDescription;
import com.unesco.core.dto.file.FileDescriptionModel;
import com.unesco.core.dto.task.TaskDescriptionModel;
import com.unesco.core.repositories.task.TaskDescriptionRepository;
import com.unesco.core.services.dataService.mapperService.MapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TaskDescriptionDataService implements ITaskDescriptionDataService
{
   @Autowired
   private MapperService _mapperService;
   @Autowired
   private TaskDescriptionRepository _taskDescriptionRepository;

   @Override
   public TaskDescriptionModel save(TaskDescriptionModel taskDescriptionModel) {
      TaskDescription forsave = (TaskDescription) _mapperService.toEntity(taskDescriptionModel);
      return (TaskDescriptionModel) _mapperService.toDto(_taskDescriptionRepository.save(forsave));
   }

   @Override
   public void delete(long id) {
      _taskDescriptionRepository.delete(id);
   }

   @Override
   public TaskDescriptionModel get(long id) {
      return (TaskDescriptionModel) _mapperService.toDto(_taskDescriptionRepository.findById(id));
   }

   @Override
   public List<TaskDescriptionModel> getAll() {
      List<TaskDescriptionModel> result = new ArrayList<>();
      Iterable<TaskDescription> entities = _taskDescriptionRepository.findAll();
      for (TaskDescription item:entities) {
         result.add((TaskDescriptionModel) _mapperService.toDto(item));
      }
      return result;
   }

   @Override
   public List<TaskDescriptionModel> getTaskDescriptionByCreator(long id) {
      Iterable<TaskDescription> entities = _taskDescriptionRepository.findAllByCreatorId(id);
      List<TaskDescriptionModel> result = new ArrayList<>();
      for(TaskDescription item:entities){
         result.add((TaskDescriptionModel) _mapperService.toDto(item));
      }
      return result;
   }

   @Override
   public void updateTaskDescription(TaskDescriptionModel td) {
      TaskDescription up = _taskDescriptionRepository.findById(td.getId());
      up.setName(td.getName());
      up.setDescription(td.getDescription());
      up.setStatus(td.getStatus());
      Set<FileDescription> files = new HashSet<>();
      for(FileDescriptionModel file: td.getFiles()){
         files.add((FileDescription) _mapperService.toEntity(file));
      }
      up.setFiles(files);
      _taskDescriptionRepository.save(up);
   }
}

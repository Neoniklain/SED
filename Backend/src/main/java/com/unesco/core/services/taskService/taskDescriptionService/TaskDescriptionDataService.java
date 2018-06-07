package com.unesco.core.services.taskService.taskDescriptionService;

import com.unesco.core.entities.task.TaskDescription;
import com.unesco.core.models.TaskDescriptionModel;
import com.unesco.core.repositories.task.TaskDescriptionRepository;
import com.unesco.core.services.mapperService.MapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskDescriptionDataService implements ITaskDescriptionDataService
{
   @Autowired
   MapperService _mapperService;
   @Autowired
   TaskDescriptionRepository _taskDescriptionRepository;

   @Override
   public TaskDescriptionModel Save(TaskDescriptionModel taskDescriptionModel) {
      TaskDescription forSave = (TaskDescription) _mapperService.toEntity(taskDescriptionModel);
      return (TaskDescriptionModel) _mapperService.toModel(_taskDescriptionRepository.save(forSave));
   }

   @Override
   public void Delete(long id) {
      _taskDescriptionRepository.delete(id);
   }

   @Override
   public TaskDescriptionModel Get(long id) {
      return (TaskDescriptionModel) _mapperService.toModel(_taskDescriptionRepository.findById(id));
   }

   @Override
   public List<TaskDescriptionModel> GetAll() {
      List<TaskDescriptionModel> result = new ArrayList<>();
      Iterable<TaskDescription> entities = _taskDescriptionRepository.findAll();
      for (TaskDescription item:entities) {
         result.add((TaskDescriptionModel) _mapperService.toModel(item));
      }
      return result;
   }

   @Override
   public List<TaskDescriptionModel> GetTaskDescriptionByCreator(long id) {
      Iterable<TaskDescription> entities = _taskDescriptionRepository.findAllByCreatorId(id);
      List<TaskDescriptionModel> result = new ArrayList<>();
      for(TaskDescription item:entities){
         result.add((TaskDescriptionModel) _mapperService.toModel(item));
      }
      return result;
   }

   @Override
   public void UpdateTaskDescription(TaskDescriptionModel td) {
      TaskDescription up = _taskDescriptionRepository.findById(td.getId());
      up.setName(td.getName());
      up.setDescription(td.getDescription());
      up.setStatus(td.getStatus());
      _taskDescriptionRepository.save(up);
   }
}

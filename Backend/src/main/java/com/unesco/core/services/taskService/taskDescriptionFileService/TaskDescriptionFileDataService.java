package com.unesco.core.services.taskService.taskDescriptionFileService;

import com.unesco.core.entities.task.TaskDescriptionFile;
import com.unesco.core.models.task.TaskDescriptionFileModel;
import com.unesco.core.repositories.task.TaskDescriptionFileRepository;
import com.unesco.core.services.mapperService.MapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskDescriptionFileDataService implements ITaskDescriptionFileDataService
{
   @Autowired
   MapperService _mapperService;
   @Autowired
   TaskDescriptionFileRepository _fileRepository;

   @Override
   public TaskDescriptionFileModel Save(TaskDescriptionFileModel taskDescriptionFileModel) {
      TaskDescriptionFile forSave = (TaskDescriptionFile) _mapperService.toEntity(taskDescriptionFileModel);
      return (TaskDescriptionFileModel) _mapperService.toModel(_fileRepository.save(forSave));
   }

   @Override
   public void Delete(long id) {
      _fileRepository.delete(id);
   }

   @Override
   public TaskDescriptionFileModel Get(long id) {
      return (TaskDescriptionFileModel) _mapperService.toModel(_fileRepository.findOne(id));
   }

   @Override
   public List<TaskDescriptionFileModel> GetAll() {
      List<TaskDescriptionFileModel> result = new ArrayList<>();
      Iterable<TaskDescriptionFile> entities = _fileRepository.findAll();
      for (TaskDescriptionFile item: entities) {
         result.add((TaskDescriptionFileModel) _mapperService.toModel(item));
      }
      return result;
   }

   @Override
   public List<TaskDescriptionFileModel> GetFilesByTaskDescriptionId(long id) {
      List<TaskDescriptionFileModel> result = new ArrayList<>();
      Iterable<TaskDescriptionFile> entities = _fileRepository.findAllByTaskDescriptionId(id);
      for (TaskDescriptionFile item: entities) {
         result.add((TaskDescriptionFileModel) _mapperService.toModel(item));
      }
      return result;
   }
}

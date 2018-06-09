package com.unesco.core.services.taskService.taskUserFileService;

import com.unesco.core.entities.task.TaskUserFile;
import com.unesco.core.models.task.TaskUserFileModel;
import com.unesco.core.repositories.task.TaskUserFileRepository;
import com.unesco.core.services.mapperService.IMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskUserFileDataService implements ITaskUserFileDataService
{
   @Autowired
   private IMapperService _mapperService;
   @Autowired
   TaskUserFileRepository _fileRepository;

   @Override
   public TaskUserFileModel Save(TaskUserFileModel taskUserFileModel) {
      TaskUserFile forSave = (TaskUserFile) _mapperService.toEntity(taskUserFileModel);
      return (TaskUserFileModel) _mapperService.toModel(_fileRepository.save(forSave));
   }

   @Override
   public void Delete(long id) {
      _fileRepository.delete(id);
   }

   @Override
   public TaskUserFileModel Get(long id) {
      return (TaskUserFileModel) _mapperService.toModel(_fileRepository.findOne(id));
   }

   @Override
   public List<TaskUserFileModel> GetAll() {
      List<TaskUserFileModel> result = new ArrayList<>();
      Iterable<TaskUserFile> entities = _fileRepository.findAll();
      for (TaskUserFile item: entities) {
         result.add((TaskUserFileModel) _mapperService.toModel(item));
      }
      return result;
   }

   @Override
   public List<TaskUserFileModel> GetFilesByTaskUserId(long id) {
      List<TaskUserFileModel> result = new ArrayList<>();
      Iterable<TaskUserFile> entities = _fileRepository.findAllByTaskUserId(id);
      for (TaskUserFile item: entities) {
         result.add((TaskUserFileModel) _mapperService.toModel(item));
      }
      return result;
   }
}

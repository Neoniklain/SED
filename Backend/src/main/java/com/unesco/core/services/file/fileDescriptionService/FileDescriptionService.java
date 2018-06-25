package com.unesco.core.services.file.fileDescriptionService;

import com.unesco.core.entities.file.FileDescription;
import com.unesco.core.dto.file.FileDescriptionModel;
import com.unesco.core.repositories.file.FileDescriptionRepository;
import com.unesco.core.services.mapperService.MapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FileDescriptionService implements IFileDescriptionService
{
   @Autowired
   MapperService _mapperService;
   @Autowired
   FileDescriptionRepository _fileDescriptionRepository;

   @Override
   public FileDescriptionModel Save(FileDescriptionModel fileDescriptionModel) {
      FileDescription forSave = (FileDescription) _mapperService.toEntity(fileDescriptionModel);
      return (FileDescriptionModel) _mapperService.toDto(_fileDescriptionRepository.save(forSave));
   }

   @Override
   public void Delete(long id) {
      _fileDescriptionRepository.delete(id);
   }

   @Override
   public FileDescriptionModel Get(long id) {
      return (FileDescriptionModel) _mapperService.toDto(_fileDescriptionRepository.findOne(id));
   }

   @Override
   public List<FileDescriptionModel> GetAll() {
      List<FileDescriptionModel> result = new ArrayList<>();
      Iterable<FileDescription> entities = _fileDescriptionRepository.findAll();
      for (FileDescription item:entities) {
         result.add((FileDescriptionModel) _mapperService.toDto(item));
      }
      return result;
   }
}

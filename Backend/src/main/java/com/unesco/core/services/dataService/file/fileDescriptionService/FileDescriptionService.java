package com.unesco.core.services.dataService.file.fileDescriptionService;

import com.unesco.core.entities.file.FileDescription;
import com.unesco.core.dto.file.FileDescriptionModel;
import com.unesco.core.repositories.file.FileDescriptionRepository;
import com.unesco.core.services.dataService.mapperService.MapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FileDescriptionService implements IFileDescriptionService
{
   @Autowired
   private MapperService _mapperService;
   @Autowired
   private FileDescriptionRepository _fileDescriptionRepository;

   @Override
   public FileDescriptionModel save(FileDescriptionModel fileDescriptionModel) {
      FileDescription forsave = (FileDescription) _mapperService.toEntity(fileDescriptionModel);
      return (FileDescriptionModel) _mapperService.toDto(_fileDescriptionRepository.save(forsave));
   }

   @Override
   public void delete(long id) {
      _fileDescriptionRepository.delete(id);
   }

   @Override
   public FileDescriptionModel get(long id) {
      return (FileDescriptionModel) _mapperService.toDto(_fileDescriptionRepository.findOne(id));
   }

   @Override
   public List<FileDescriptionModel> getAll() {
      List<FileDescriptionModel> result = new ArrayList<>();
      Iterable<FileDescription> entities = _fileDescriptionRepository.findAll();
      for (FileDescription item:entities) {
         result.add((FileDescriptionModel) _mapperService.toDto(item));
      }
      return result;
   }
}

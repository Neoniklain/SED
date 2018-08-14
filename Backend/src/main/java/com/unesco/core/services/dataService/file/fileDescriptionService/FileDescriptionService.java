package com.unesco.core.services.dataService.file.fileDescriptionService;

import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.enums.StatusTypes;
import com.unesco.core.dto.file.FileDescriptionModel;
import com.unesco.core.entities.file.FileDescription;
import com.unesco.core.repositories.file.FileDescriptionRepository;
import com.unesco.core.services.dataService.mapperService.MapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
   public ResponseStatusDTO<FileDescriptionModel> save(FileDescriptionModel fileDescriptionModel) {
      FileDescription entity = (FileDescription) _mapperService.toEntity(fileDescriptionModel);
      ResponseStatusDTO<FileDescriptionModel> result = new ResponseStatusDTO<>(StatusTypes.OK);
      try {
         entity = _fileDescriptionRepository.save(entity);
      } catch (Exception e) {
         result.setStatus(StatusTypes.ERROR);
         result.addErrors(e.getMessage());
         return result;
      }
      result.setData((FileDescriptionModel) _mapperService.toDto(entity));
      return result;
   }

   @Override
   public ResponseStatusDTO<FileDescriptionModel> delete(long id) {
      ResponseStatusDTO<FileDescriptionModel> result = new ResponseStatusDTO<>(StatusTypes.OK);
      try {
         _fileDescriptionRepository.delete(id);
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

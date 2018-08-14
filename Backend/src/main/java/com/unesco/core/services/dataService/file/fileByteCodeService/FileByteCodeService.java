package com.unesco.core.services.dataService.file.fileByteCodeService;

import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.enums.StatusTypes;
import com.unesco.core.dto.file.FileByteCodeModel;
import com.unesco.core.entities.file.FileByteCode;
import com.unesco.core.repositories.file.FileByteCodeRepository;
import com.unesco.core.services.dataService.mapperService.MapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FileByteCodeService implements IFileByteCodeService
{
   @Autowired
   private MapperService _mapperService;
   @Autowired
   private FileByteCodeRepository _fileByteCodeRepository;
   @Override
   public ResponseStatusDTO<FileByteCodeModel> save(FileByteCodeModel fileDescriptionModel) {
      FileByteCode entity = (FileByteCode) _mapperService.toEntity(fileDescriptionModel);
      ResponseStatusDTO<FileByteCodeModel> result = new ResponseStatusDTO<>(StatusTypes.OK);
      try {
         entity = _fileByteCodeRepository.save(entity);
      } catch (Exception e) {
         result.setStatus(StatusTypes.ERROR);
         result.addErrors(e.getMessage());
         return result;
      }
      result.setData((FileByteCodeModel) _mapperService.toDto(entity));
      return result;
   }

   @Override
   public ResponseStatusDTO<FileByteCodeModel> delete(long id) {
      ResponseStatusDTO<FileByteCodeModel> result = new ResponseStatusDTO<>(StatusTypes.OK);
      try {
         _fileByteCodeRepository.delete(id);
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
   public FileByteCodeModel get(long id) {
      return (FileByteCodeModel) _mapperService.toDto(_fileByteCodeRepository.findOne(id));
   }

   @Override
   public List<FileByteCodeModel> getAll() {
      List<FileByteCodeModel> result = new ArrayList<>();
      Iterable<FileByteCode> entities = _fileByteCodeRepository.findAll();
      for (FileByteCode item:entities) {
         result.add((FileByteCodeModel) _mapperService.toDto(item));
      }
      return result;
   }

   @Override
   public FileByteCodeModel getByDescriptionId(long id) {
      return (FileByteCodeModel) _mapperService.toDto(_fileByteCodeRepository.findByFileDescription_Id(id));
   }
}

package com.unesco.core.services.file.fileByteCodeService;

import com.unesco.core.entities.file.FileByteCode;
import com.unesco.core.models.file.FileByteCodeModel;
import com.unesco.core.repositories.file.FileByteCodeRepository;
import com.unesco.core.services.mapperService.MapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FileByteCodeService implements IFileByteCodeService
{
   @Autowired
   MapperService _mapperService;
   @Autowired
   FileByteCodeRepository _fileByteCodeRepository;
   @Override
   public FileByteCodeModel Save(FileByteCodeModel fileDescriptionModel) {
      FileByteCode forSave = (FileByteCode) _mapperService.toEntity(fileDescriptionModel);
      return (FileByteCodeModel) _mapperService.toModel(_fileByteCodeRepository.save(forSave));
   }

   @Override
   public void Delete(long id) {
      _fileByteCodeRepository.delete(id);
   }

   @Override
   public FileByteCodeModel Get(long id) {
      return (FileByteCodeModel) _mapperService.toModel(_fileByteCodeRepository.findOne(id));
   }

   @Override
   public List<FileByteCodeModel> GetAll() {
      List<FileByteCodeModel> result = new ArrayList<>();
      Iterable<FileByteCode> entities = _fileByteCodeRepository.findAll();
      for (FileByteCode item:entities) {
         result.add((FileByteCodeModel) _mapperService.toModel(item));
      }
      return result;
   }

   @Override
   public FileByteCodeModel GetByDescriptionId(long id) {
      return (FileByteCodeModel) _mapperService.toModel(_fileByteCodeRepository.findByFileDescription_Id(id));
   }
}

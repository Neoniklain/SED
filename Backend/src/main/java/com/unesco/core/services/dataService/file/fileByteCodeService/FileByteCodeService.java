package com.unesco.core.services.dataService.file.fileByteCodeService;

import com.unesco.core.entities.file.FileByteCode;
import com.unesco.core.dto.file.FileByteCodeModel;
import com.unesco.core.repositories.file.FileByteCodeRepository;
import com.unesco.core.services.dataService.mapperService.MapperService;
import org.springframework.beans.factory.annotation.Autowired;
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
   public FileByteCodeModel save(FileByteCodeModel fileDescriptionModel) {
      FileByteCode forsave = (FileByteCode) _mapperService.toEntity(fileDescriptionModel);
      return (FileByteCodeModel) _mapperService.toDto(_fileByteCodeRepository.save(forsave));
   }

   @Override
   public void delete(long id) {
      _fileByteCodeRepository.delete(id);
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

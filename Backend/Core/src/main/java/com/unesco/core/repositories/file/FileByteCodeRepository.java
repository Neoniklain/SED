package com.unesco.core.repositories.file;

import com.unesco.core.entities.file.FileByteCode;
import org.springframework.data.repository.CrudRepository;


public interface FileByteCodeRepository extends CrudRepository<FileByteCode, Long> {
    FileByteCode findByFileDescription_Id(long id);
}
package com.unesco.core.repositories.file;

import com.unesco.core.entities.file.FileDescription;
import org.springframework.data.repository.CrudRepository;

public interface FileDescriptionRepository extends CrudRepository<FileDescription, Long> {
}
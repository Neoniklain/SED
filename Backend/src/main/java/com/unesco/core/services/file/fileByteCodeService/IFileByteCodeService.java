package com.unesco.core.services.file.fileByteCodeService;


import com.unesco.core.entities.file.FileByteCode;
import com.unesco.core.models.file.FileByteCodeModel;
import com.unesco.core.services.IDataService;

public interface IFileByteCodeService extends IDataService<FileByteCodeModel> {
    /**
     * Получить ДТО файла по id его описания
     * @param id id описания файла
     * @return
     */
    FileByteCodeModel GetByDescriptionId(long id);
}
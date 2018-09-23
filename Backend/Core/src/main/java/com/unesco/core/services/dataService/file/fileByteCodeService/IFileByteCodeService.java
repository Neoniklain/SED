package com.unesco.core.services.dataService.file.fileByteCodeService;


import com.unesco.core.dto.file.FileByteCodeModel;
import com.unesco.core.services.dataService.IDataService;

public interface IFileByteCodeService extends IDataService<FileByteCodeModel> {
    /**
     * Получить ДТО файла по id его описания
     * @param id id описания файла
     * @return
     */
    FileByteCodeModel getByDescriptionId(long id);
}

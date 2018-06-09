package com.unesco.core.services.taskService.taskDescriptionFileService;

import com.unesco.core.models.task.TaskDescriptionFileModel;
import com.unesco.core.services.IDataService;

import java.util.List;

public interface ITaskDescriptionFileDataService extends IDataService<TaskDescriptionFileModel> {
    /**
     * Получить список файлов для указанного описания задачи
     * @param id id описания задачи
     * @return
     */
    List<TaskDescriptionFileModel> GetFilesByTaskDescriptionId(long id);
}

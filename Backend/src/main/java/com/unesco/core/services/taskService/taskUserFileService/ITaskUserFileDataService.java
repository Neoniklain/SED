package com.unesco.core.services.taskService.taskUserFileService;

import com.unesco.core.entities.task.TaskUserFile;
import com.unesco.core.models.task.TaskUserFileModel;
import com.unesco.core.services.IDataService;

import java.util.List;

public interface ITaskUserFileDataService extends IDataService<TaskUserFileModel> {
    /**
     * Получить список файлов для указанной реализации задачи
     * @param id id конкретной реализации задачи
     * @return
     */
    List<TaskUserFileModel> GetFilesByTaskUserId(long id);
}

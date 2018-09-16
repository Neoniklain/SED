package com.unesco.core.controller;

import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.enums.StatusTypes;
import com.unesco.core.dto.enums.TaskStatusType;
import com.unesco.core.dto.task.TaskDescriptionDTO;
import com.unesco.core.dto.task.TaskUserDTO;
import com.unesco.core.entities.task.TaskDescription;
import com.unesco.core.managers.task.interfaces.ITaskManager;
import com.unesco.core.services.userService.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class TaskController {
    @Autowired
    private ITaskManager _taskManager;
    @Autowired
    private IUserService userService;

    public ResponseStatusDTO getListExecutor() {
        ResponseStatusDTO result = new ResponseStatusDTO(StatusTypes.ERROR);
        try {
            result.setData(_taskManager.getTaskDescByExecutor(userService.getCurrentUser().getId()));
            result.setStatus(StatusTypes.OK);
        }
        catch (Exception e) {
            result = new ResponseStatusDTO(StatusTypes.ERROR);
            result.addErrors("Не удалось получить список назначенных задач");
        }
        return result;
    }

    public ResponseStatusDTO getListCreator() {
        ResponseStatusDTO result = new ResponseStatusDTO(StatusTypes.ERROR);
        try {
            result.setData(_taskManager.getTaskDescByCreator(userService.getCurrentUser().getId(),false));
            result.setStatus(StatusTypes.OK);
        }
        catch (Exception e) {
            result = new ResponseStatusDTO(StatusTypes.ERROR);
            result.addErrors("Не удалось получить список созданных задач");
        }
        return result;
    }

    public ResponseStatusDTO create(TaskDescriptionDTO newTask) {
        return _taskManager.createNewTask(newTask);
    }

    public ResponseStatusDTO changeStatusTaskDesc(long td_id, int status_id) {
        return _taskManager.changeStatusTaskDesc(td_id, status_id);
    }

    public ResponseStatusDTO changeStatusTaskUser(long tu_id, int status_id) {
        return _taskManager.changeStatusTaskUser(tu_id, status_id);
    }

    public ResponseStatusDTO getTaskDesc(long id) {
        ResponseStatusDTO result = new ResponseStatusDTO(StatusTypes.ERROR);
        try {
            result.setData(_taskManager.getTaskDescById(id,false));
            result.setStatus(StatusTypes.OK);
        }
        catch (Exception e) {
            result = new ResponseStatusDTO(StatusTypes.ERROR);
            result.addErrors("Не удалось получить описание задачи");
        }
        return result;
    }

    public ResponseStatusDTO getTaskUser(long id) {
        ResponseStatusDTO result = new ResponseStatusDTO(StatusTypes.ERROR);
        try {
            result.setData(_taskManager.getTaskUserById(id));
            result.setStatus(StatusTypes.OK);
        }
        catch (Exception e) {
            result = new ResponseStatusDTO(StatusTypes.ERROR);
            result.addErrors("Не удалось получить реализацию задачи");
        }
        return result;
    }

    public ResponseStatusDTO updateTaskDesc(TaskDescriptionDTO task) {
        TaskDescriptionDTO forUpdate = new TaskDescriptionDTO();
        forUpdate.setDateRequired(task.getDateRequired());
        forUpdate.setName(task.getName());
        forUpdate.setDescription(task.getDescription());
        return _taskManager.updateTaskDesc(forUpdate);
    }

    public ResponseStatusDTO changeResponseTaskUser(TaskUserDTO task) {
        TaskUserDTO forChange = new TaskUserDTO();
        forChange.setResponse(task.getResponse());
        forChange.setStatus(TaskStatusType.SentToReview.ordinal());
        Date date = new Date();
        long time = date.getTime();
        Timestamp now = new Timestamp(time);
        forChange.setDateCreate(now);
        return _taskManager.updateTaskUser(forChange);
    }

    public ResponseStatusDTO deleteTaskDesc(long id) {
        return _taskManager.deleteTaskDesc(id);
    }

    public ResponseStatusDTO deleteTaskUser(long id) {
        return _taskManager.deleteTaskUser(id);
    }
}

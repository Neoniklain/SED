package com.unesco.core.controller;

import com.unesco.core.dto.account.UserDTO;
import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.enums.StatusTypes;
import com.unesco.core.dto.enums.TaskStatusType;
import com.unesco.core.dto.task.TaskDescriptionDTO;
import com.unesco.core.dto.task.TaskUserDTO;
import com.unesco.core.entities.task.TaskDescription;
import com.unesco.core.entities.task.TaskUser;
import com.unesco.core.managers.task.interfaces.ITaskManager;
import com.unesco.core.services.userService.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public ResponseStatusDTO getListAll() {
        ResponseStatusDTO result = new ResponseStatusDTO(StatusTypes.ERROR);
        try {
            result.setData(_taskManager.getAllTaskDesc(false));
            result.setStatus(StatusTypes.OK);
        }
        catch (Exception e) {
            result = new ResponseStatusDTO(StatusTypes.ERROR);
            result.addErrors("Не удалось получить список созданных задач");
        }
        return result;
    }

    public ResponseStatusDTO create(TaskDescriptionDTO newTask) {
        newTask.setCreator(userService.getCurrentUser());
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

    /**
     * Получить реализации для указанной задачи
     * @param id id задачи
     * @param isForExecutor получить для исполнителя? 1 - да
     * @return Список реализаций задачи
     */
    public ResponseStatusDTO GetTaskUserByTD(long id, long isForExecutor) {
        ResponseStatusDTO result = new ResponseStatusDTO(StatusTypes.ERROR);
        try {
            List<TaskUserDTO> items = _taskManager.getTaskUserByTDID(id);
            if (isForExecutor == 1){
                UserDTO executor = userService.getCurrentUser();
                TaskUserDTO item = items.stream()
                        .filter(x -> x.getExecutor().getId() == executor.getId())
                        .findFirst()
                        .get();
                items = new ArrayList<>();
                items.add(item);
            }
            result.setData(items);
            result.setStatus(StatusTypes.OK);
        }
        catch (Exception e) {
            result = new ResponseStatusDTO(StatusTypes.ERROR);
            result.addErrors("Не удалось получить реализации задачи");
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
        forChange.setId(task.getId());
        forChange.setResponse(task.getResponse());
        Date date = new Date();
        long time = date.getTime();
        Timestamp now = new Timestamp(time);
        forChange.setDateCreate(now);
        ResponseStatusDTO result = _taskManager.updateTaskUser(forChange);
        if (result.getStatus() == StatusTypes.OK) {
            result.setData(this.changeStatusTaskUser(task.getId(), TaskStatusType.SentToReview.ordinal()).getData());
            result.setMessage(new ArrayList<>());
            result.addMessage("Ответ отправлен");
        }
        return result;
    }

    public ResponseStatusDTO deleteTaskDesc(long id) {
        return _taskManager.deleteTaskDesc(id);
    }

    public ResponseStatusDTO deleteTaskUser(long id) {
        return _taskManager.deleteTaskUser(id);
    }
}

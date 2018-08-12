package com.unesco.core.controller;

import com.unesco.core.dto.account.UserDTO;
import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.enums.StatusTypes;
import com.unesco.core.dto.task.TaskDescriptionModel;
import com.unesco.core.dto.task.TaskUserModel;
import com.unesco.core.managers.task.interfaces.ITaskService;
import com.unesco.core.services.userService.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskController {
    @Autowired
    private ITaskService _TaskDataService;
    @Autowired
    private IUserService userService;

    public ResponseStatusDTO getList() {
        long userId = userService.getCurrentUser().getId();
        List<TaskDescriptionModel> res = _TaskDataService.getTaskDescriptionByCreator(userId);
        List<TaskUserModel> myTasks = _TaskDataService.getTaskUsersByExecutor(userId);
        for (TaskUserModel item: myTasks) {
            res.add(_TaskDataService.getTaskDescriptionById(item.getTaskDescriptionId()));
        }
        for (TaskDescriptionModel item:res){
            if(item.getCreator().getId() == userId){
                List<UserDTO> temp_users = new ArrayList<>();
                List<TaskUserModel> ltu = _TaskDataService.getTaskUsersForTaskDescription(item.getId());
                item.setTaskUsers(ltu);
                for (TaskUserModel T: item.getTaskUsers()) {
                    temp_users.add(T.getExecutor());
                }
                item.setUsers(temp_users);
            }
            else{
                item.setTaskUsers(myTasks.stream()
                        .filter(x->x.getTaskDescriptionId() == item.getId())
                        .collect(Collectors.toList()));
            }
        }
        ResponseStatusDTO result = new ResponseStatusDTO(StatusTypes.OK);
        result.addMessage ("Список задач");
        result.setData(res);
        return result;
    }

    public ResponseStatusDTO create(TaskDescriptionModel newTask) {
        newTask.setCreator(userService.getCurrentUser());
        ResponseStatusDTO result = new ResponseStatusDTO();
        result.setData(_TaskDataService.createNewTaskDescription(newTask));
        if(result.getData() == null){
            result.setStatus(StatusTypes.ERROR);
            result.addErrors("Ошибка создания задачи");
        }
        else{
            result.setStatus(StatusTypes.OK);
            result.addMessage("Задача создана");
        }
        return result;
    }

    public ResponseStatusDTO answer(TaskUserModel item) {
        _TaskDataService.answerTaskUser(item);
        ResponseStatusDTO result = new ResponseStatusDTO(StatusTypes.OK);
        result.addMessage("Ответ отправлен");
        result.setData(item);
        return result;
    }

    public ResponseStatusDTO changeStatus(long tu_id, int status_id) {
        _TaskDataService.changeStatusTaskUser(tu_id, status_id);
        ResponseStatusDTO result = new ResponseStatusDTO(StatusTypes.OK);
        result.addMessage("Статус изменён");
        return result;
    }

    public ResponseStatusDTO get(long id) {
        ResponseStatusDTO result = new ResponseStatusDTO(StatusTypes.OK);
        result.setData(_TaskDataService.getTaskDescriptionById(id));
        return result;
    }

    public ResponseStatusDTO update(TaskDescriptionModel task) {
        _TaskDataService.updateTaskDescription(task);
        ResponseStatusDTO result = new ResponseStatusDTO(StatusTypes.OK);
        result.addMessage("Задача обновлена");
        result.setData(task);
        return result;
    }

    public ResponseStatusDTO delete(long id) {
        _TaskDataService.deleteTaskDescription(id);
        ResponseStatusDTO result = new ResponseStatusDTO(StatusTypes.OK);
        result.addMessage("Задача удалена");
        return result;
    }
}

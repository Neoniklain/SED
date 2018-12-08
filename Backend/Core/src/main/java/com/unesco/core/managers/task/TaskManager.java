package com.unesco.core.managers.task;

import com.unesco.core.dto.account.UserDTO;
import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.enums.StatusTypes;
import com.unesco.core.dto.enums.TaskStatusType;
import com.unesco.core.dto.enums.TaskType;
import com.unesco.core.dto.task.TaskDescriptionDTO;
import com.unesco.core.dto.task.TaskUserDTO;
import com.unesco.core.managers.task.interfaces.ITaskManager;
import com.unesco.core.services.dataService.account.userService.IUserDataService;
import com.unesco.core.services.mapperService.MapperService;
import com.unesco.core.services.dataService.taskService.taskDescriptionService.ITaskDescriptionDataService;
import com.unesco.core.services.dataService.taskService.taskUserService.ITaskUserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TaskManager implements ITaskManager {
    @Autowired
    ITaskDescriptionDataService _TDService;
    @Autowired
    ITaskUserDataService _TUService;
    @Autowired
    IUserDataService _userDataService;
    @Autowired
    IUserDataService _userService;

    @Override
    public ResponseStatusDTO<TaskDescriptionDTO> createNewTask(TaskDescriptionDTO td) {
        ResponseStatusDTO<TaskDescriptionDTO> result;

        if (td.getTaskUsers().size() == 0) {
            result = new ResponseStatusDTO<>(StatusTypes.ERROR);
            result.addErrors("Пользователи не указаны");
            return result;
        }

        TaskStatusType taskStatusType;
        if (td.getType() == TaskType.Info.ordinal()) {
            taskStatusType = TaskStatusType.Completed;
        } else {
            taskStatusType = TaskStatusType.Processed;
        }
        td.setStatus(taskStatusType.ordinal());
        result = _TDService.save(td);
        if (result.getStatus() == StatusTypes.OK) {
            TaskDescriptionDTO saved = result.getData();
            List<TaskUserDTO> ltu = new ArrayList<>();
            for (TaskUserDTO TU : td.getTaskUsers()) {
                if (TU.getExecutor().getId() != saved.getCreator().getId()) {
                    TaskUserDTO newTUDTO = new TaskUserDTO();
                    newTUDTO.setResponse(null);
                    newTUDTO.setStatus(taskStatusType.ordinal());
                    newTUDTO.setExecutor(TU.getExecutor());
                    newTUDTO.setTaskDescriptionId(saved.getId());
                    newTUDTO.setDateRequired(TU.getDateRequired());
                    ResponseStatusDTO<TaskUserDTO> tuSaveRes = _TUService.save(newTUDTO);
                    if (tuSaveRes.getStatus() == StatusTypes.OK) {
                        ltu.add(tuSaveRes.getData());
                    } else {
                        for (String errorMSG : tuSaveRes.getErrors()) {
                            result.addErrors(errorMSG);
                        }
                    }
                }
            }
            saved.setTaskUsers(ltu);
            result.setData(saved);
            return result;
        }
        else {
            return result;
        }
    }

    @Override
    public ResponseStatusDTO<TaskDescriptionDTO> deleteTaskDesc(long id) {
        ResponseStatusDTO<TaskDescriptionDTO> result;
        try {
            List<TaskUserDTO> subTasks = _TUService.getByTDID(id);
            for (TaskUserDTO item : subTasks) {
                _TUService.delete(item.getId());
            }
            return _TDService.delete(id);
        }
        catch (Exception e) {
            result = new ResponseStatusDTO<>(StatusTypes.ERROR);
            if (e instanceof DataIntegrityViolationException) {
                result.addErrors("Удаление не удалось. У объекта есть зависимости.");
            }
            result.addErrors("Удаление не удалось");
        }
        return result;
    }

    @Override
    public ResponseStatusDTO<TaskDescriptionDTO> updateTaskDesc(TaskDescriptionDTO td) {
        return _TDService.update(td);
    }

    @Override
    public List<TaskDescriptionDTO> getAllTaskDesc(boolean isIncludeTU) {
        return _TDService.getAll();
    }

    @Override
    public TaskDescriptionDTO getTaskDescById(long id, boolean isIncludeTU) {
        TaskDescriptionDTO result = _TDService.get(id);
        if (isIncludeTU) {
            result.setTaskUsers(_TUService.getByTDID(id));
        }
        return result;
    }

    @Override
    public List<TaskDescriptionDTO> getTaskDescByExecutor(long id) {
        List<TaskUserDTO> tudtos = _TUService.getByExecutor(id);

        List<TaskDescriptionDTO> result = new ArrayList<>();
        for (TaskUserDTO item : tudtos) {
            TaskDescriptionDTO td = _TDService.get(item.getTaskDescriptionId());
            List<TaskUserDTO> tus = new ArrayList<>();
            tus.add(item);
            td.setTaskUsers(tus);
            result.add(td);
        }
        return result;
    }

    @Override
    public List<TaskDescriptionDTO> getTaskDescByCreator(long id, boolean isIncludeTU) {
        List<TaskDescriptionDTO> result = _TDService.getByCreator(id);
        if (isIncludeTU) {
            for (TaskDescriptionDTO item : result) {
                item.setTaskUsers(_TUService.getByTDID(item.getId()));
            }
        }
        return result;
    }

    @Override
    public ResponseStatusDTO<TaskDescriptionDTO> changeStatusTaskDesc(long td_id, int status_id) {
        return _TDService.changeStatus(td_id, status_id);
    }

    @Override
    public ResponseStatusDTO<TaskUserDTO> deleteTaskUser(long id) {
        ResponseStatusDTO<TaskUserDTO> result = _TUService.delete(id);
        if(result.getData()!=null) {
            this.CheckTaskDescStatus(result.getData().getTaskDescriptionId());
        }
        return result;
    }

    @Override
    public TaskUserDTO getTaskUserById(long id) {
        return _TUService.get(id);
    }

    @Override
    public ResponseStatusDTO<TaskUserDTO> updateTaskUser(TaskUserDTO tu) {
        return _TUService.update(tu);
    }

    @Override
    public List<TaskUserDTO> getTaskUserByTDID(long id) {
        return _TUService.getByTDID(id);
    }

    @Override
    public ResponseStatusDTO<TaskUserDTO> changeStatusTaskUser(long tu_id, int status_id) {
        ResponseStatusDTO<TaskUserDTO> result = _TUService.changeStatus(tu_id, status_id);
        CheckTaskDescStatus(result.getData().getTaskDescriptionId());
        return result;
    }

    private void CheckTaskDescStatus(long td_id) {
        TaskDescriptionDTO task = this.getTaskDescById(td_id, true);
        if(task.getStatus() != TaskStatusType.Completed.ordinal()) {
            int total_count_TU = task.getTaskUsers().size();
            int total_count_completed = 0;
            for(TaskUserDTO tu: task.getTaskUsers()) {
                if (tu.getStatus() == TaskStatusType.Completed.ordinal()) {
                    total_count_completed++;
                }
                if (tu.getStatus() == TaskStatusType.Denied.ordinal()) {
                    total_count_completed++;
                }
            }
            if (total_count_completed == total_count_TU) {
                this.changeStatusTaskDesc(td_id, TaskStatusType.Completed.ordinal());
            }
        }
    }
}

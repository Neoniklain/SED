package com.unesco.core.controller;

import com.unesco.core.models.task.TaskDescriptionModel;
import com.unesco.core.models.task.TaskUserModel;
import com.unesco.core.models.account.UserModel;
import com.unesco.core.models.additional.ResponseStatus;
import com.unesco.core.security.CustomUserDetailsService;
import com.unesco.core.managers.task.interfaces.ITaskService;
import com.unesco.core.utils.StatusTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskController {
    @Autowired
    private ITaskService _TaskDataService;
    @Autowired
    private CustomUserDetailsService _CustomUserDetailsService;

    public ResponseStatus GetList() {
        long userId = _CustomUserDetailsService.getUserDetails().getId();
        List<TaskDescriptionModel> res = _TaskDataService.getTaskDescriptionByCreator(userId);
        List<TaskUserModel> myTasks = _TaskDataService.getTaskUsersByExecutor(userId);
        for (TaskUserModel item: myTasks) {
            res.add(_TaskDataService.getTaskDescriptionById(item.getTaskDescriptionId()));
        }
        for (TaskDescriptionModel item:res){
            if(item.getCreator().getId() == userId){
                List<UserModel> temp_users = new ArrayList<>();
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
        ResponseStatus result = new ResponseStatus(StatusTypes.OK);
        result.addMessage ("Список задач");
        result.setData(res);
        return result;
    }

    public ResponseStatus Create(TaskDescriptionModel newTask) {
        newTask.setCreator(new UserModel(_CustomUserDetailsService.getUserDetails()));
        ResponseStatus result = new ResponseStatus(StatusTypes.OK);
        result.addMessage("Задача создана");
        result.setData(_TaskDataService.createNewTaskDescription(newTask));
        return result;
    }

    public ResponseStatus Answer(TaskUserModel item) {
        _TaskDataService.changeStatusTaskUser(item);
        ResponseStatus result = new ResponseStatus(StatusTypes.OK);
        result.addMessage("Ответ отправлен");
        result.setData(item);
        return result;
    }

    public ResponseStatus Get(long id) {
        ResponseStatus result = new ResponseStatus(StatusTypes.OK);
        result.setData(_TaskDataService.getTaskDescriptionById(id));
        return result;
    }

    public ResponseStatus Update(TaskDescriptionModel task) {
        _TaskDataService.updateTaskDescription(task);
        ResponseStatus result = new ResponseStatus(StatusTypes.OK);
        result.addMessage("Задача обновлена");
        result.setData(task);
        return result;
    }

    public ResponseStatus Delete(long id) {
        _TaskDataService.deleteTaskDescription(id);
        ResponseStatus result = new ResponseStatus(StatusTypes.OK);
        result.addMessage("Задача удалена");
        return result;
    }

    /*public ResponseStatus AddFile(long id, MultipartFile file) {
        try {
            TaskDescriptionFile temp = new TaskDescriptionFile();
            TaskDescriptionModel item =  _TaskDataService.getTaskDescriptionById(id);
            temp.setData(file.getBytes());
            temp.setTaskDescriptionId(item.getId());
            temp.setFileName(file.getOriginalFilename());
            temp.setFileType(file.getContentType());
            _fileRep.save(temp);
        } catch (IOException e) {
            e.printStackTrace();
            ResponseStatus result = new ResponseStatus(StatusTypes.ERROR);
            result.addErrors("Ошибка добавления файла");
        }
        ResponseStatus result = new ResponseStatus(StatusTypes.OK);
        result.addMessage("Файл добавлен");
        return result;
    }

    public ResponseStatus GetFile(long id) {
        //Iterable<TaskDescriptionFile> res = _fileRep.findAll();
        ResponseStatus result = new ResponseStatus(StatusTypes.OK);
        result.addMessage("Файл добавлен");
        //result.setData(res);
        return result;
    }

    public TaskDescriptionFile DownloadFile(long id) {
        Iterable<TaskDescriptionFile> res = _fileRep.findAll();
        for(TaskDescriptionFile item: res){
            return item;
        }
        return null;
    }*/
}

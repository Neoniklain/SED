package com.unesco.core.controller;


import com.unesco.core.models.TaskDescriptionModel;
import com.unesco.core.models.TaskUserModel;
import com.unesco.core.models.account.UserModel;
import com.unesco.core.models.additional.ResponseStatus;
import com.unesco.core.security.CustomUserDetailsService;
import com.unesco.core.services.taskService.ITaskService;
import com.unesco.core.utils.StatusTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/task")
public class TaskController {


    @Autowired
    private ITaskService _TaskDataService;
    @Autowired
    private CustomUserDetailsService _CustomUserDetailsService;

    @GetMapping(value = "/list")
    public Iterable<TaskDescriptionModel> GetList() {
        /*UserModel user = new UserModel(_CustomUserDetailsService.getUserDetails());
        Iterable<TaskDescriptionModel> result =  new ArrayList<>();
        List<String> role = new ArrayList<RoleModel>(user.getRoles())
                .stream()
                .map(RoleModel::getRoleName)
                .collect(Collectors.toList());*/
        Iterable<TaskDescriptionModel> res = _TaskDataService.getAllTaskDescription();
        for (TaskDescriptionModel TD: res) {
            List<UserModel> temp_users = new ArrayList<>();
            for (TaskUserModel T: TD.getTaskUsers()) {
                temp_users.add(T.getExecutor());
            }
            TD.setUsers(temp_users);
        }
        return res;
    }

    @RequestMapping(value = "/create")
    public ResponseStatus Create(@RequestBody TaskDescriptionModel newTask) {
        newTask.setCreator(new UserModel(_CustomUserDetailsService.getUserDetails()));
        _TaskDataService.createNewTaskDescription(newTask);
        return new ResponseStatus(StatusTypes.OK);
    }

    @RequestMapping(value = "/answer")
    public ResponseStatus Answer(@RequestBody TaskUserModel item) {
        _TaskDataService.changeStatusTaskUser(item);
        return new ResponseStatus(StatusTypes.OK);
    }

    @RequestMapping(value = "/get/{id}")
    public TaskDescriptionModel Get(@PathVariable("id") long id) {
        return _TaskDataService.getTaskDescriptionById(id);
    }

    @RequestMapping(value = "/update")
    public TaskDescriptionModel Update(@RequestBody TaskDescriptionModel newTask) {
        _TaskDataService.updateTaskDescription(newTask);
        return newTask;
    }

    @RequestMapping(value = "/delete/{id}")
    public ResponseStatus Delete(@PathVariable("id") long id) {
        _TaskDataService.deleteTaskDescription(id);
        return new ResponseStatus(StatusTypes.OK);
    }
}

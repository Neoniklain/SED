package com.unesco.core.controller;


import com.unesco.core.entities.workflow.TaskDescription;
import com.unesco.core.models.TaskDescriptionModel;
import com.unesco.core.models.TaskModel;
import com.unesco.core.models.account.RoleModel;
import com.unesco.core.models.account.UserModel;
import com.unesco.core.models.additional.JSONResponseStatus;
import com.unesco.core.security.CustomUserDetailsService;
import com.unesco.core.services.taskDataService.ITaskDataService;
import com.unesco.core.services.taskDataService.TaskDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("api/task")
public class TaskController {


    @Qualifier("taskDataService")
    @Autowired
    private ITaskDataService _TaskDataService;
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
            for (TaskModel T: TD.getSubTasks()) {
                temp_users.add(T.getExecutor());
            }
            TD.setUsers(temp_users);
        }
        return res;
    }

    @RequestMapping(value = "/create")
    public JSONResponseStatus Create(@RequestBody TaskDescriptionModel newTask) {
        newTask.setCreator(new UserModel(_CustomUserDetailsService.getUserDetails()));
        _TaskDataService.createNewTaskDescription(newTask);
        return JSONResponseStatus.OK();
    }

    @RequestMapping(value = "/answer")
    public JSONResponseStatus Answer(@RequestBody TaskModel item) {
        _TaskDataService.answerTask(item);
        return JSONResponseStatus.OK();
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
    public JSONResponseStatus Delete(@PathVariable("id") long id) {
        _TaskDataService.deleteTaskDescription(id);
        return JSONResponseStatus.OK();
    }
}

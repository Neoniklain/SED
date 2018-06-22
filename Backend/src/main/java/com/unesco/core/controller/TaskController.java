package com.unesco.core.controller;


import com.unesco.core.models.TaskDescriptionModel;
import com.unesco.core.models.TaskModel;
import com.unesco.core.models.account.UserDTO;
import com.unesco.core.models.additional.ResponseStatusDTO;
import com.unesco.core.security.CustomUserDetailsService;
import com.unesco.core.services.taskService.ITaskDataService;
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
    private ITaskDataService _TaskDataService;
    @Autowired
    private CustomUserDetailsService _CustomUserDetailsService;

    @GetMapping(value = "/list")
    public Iterable<TaskDescriptionModel> GetList() {
        /*UserDTO user = new UserDTO(_CustomUserDetailsService.getUserDetails());
        Iterable<TaskDescriptionModel> result =  new ArrayList<>();
        List<String> role = new ArrayList<RoleDTO>(user.getRoleEntities())
                .stream()
                .map(RoleDTO::getRoleName)
                .collect(Collectors.toList());*/
        Iterable<TaskDescriptionModel> res = _TaskDataService.getAllTaskDescription();
        for (TaskDescriptionModel TD: res) {
            List<UserDTO> temp_users = new ArrayList<>();
            for (TaskModel T: TD.getSubTasks()) {
                temp_users.add(T.getExecutor());
            }
            TD.setUsers(temp_users);
        }
        return res;
    }

    @RequestMapping(value = "/create")
    public ResponseStatusDTO Create(@RequestBody TaskDescriptionModel newTask) {
        newTask.setCreator(new UserDTO(_CustomUserDetailsService.getUserDetails()));
        _TaskDataService.createNewTaskDescription(newTask);
        return new ResponseStatusDTO(StatusTypes.OK);
    }

    @RequestMapping(value = "/answer")
    public ResponseStatusDTO Answer(@RequestBody TaskModel item) {
        _TaskDataService.answerTask(item);
        return new ResponseStatusDTO(StatusTypes.OK);
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
    public ResponseStatusDTO Delete(@PathVariable("id") long id) {
        _TaskDataService.deleteTaskDescription(id);
        return new ResponseStatusDTO(StatusTypes.OK);
    }
}

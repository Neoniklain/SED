package com.unesco.core.controllerWeb;


import com.unesco.core.controller.TaskController;
import com.unesco.core.models.task.TaskDescriptionModel;
import com.unesco.core.models.task.TaskUserModel;
import com.unesco.core.models.additional.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("api/task")
public class TaskControllerWeb {

    @Autowired
    TaskController _taskController;

    @GetMapping(value = "/list")
    public ResponseStatus GetList() {
        return _taskController.GetList();
    }

    @RequestMapping(value = "/create")
    public ResponseStatus Create(@RequestBody TaskDescriptionModel newTask) {
        return _taskController.Create(newTask);
    }

    @RequestMapping(value = "/answer")
    public ResponseStatus Answer(@RequestBody TaskUserModel item) {
        return _taskController.Answer(item);
    }

    @RequestMapping(value = "/get/{id}")
    public ResponseStatus Get(@PathVariable("id") long id) {
        return _taskController.Get(id);
    }

    @RequestMapping(value = "/update")
    public ResponseStatus Update(@RequestBody TaskDescriptionModel task) {
        return _taskController.Update(task);
    }

    @RequestMapping(value = "/delete/{id}")
    public ResponseStatus Delete(@PathVariable("id") long id) {
        return _taskController.Delete(id);
    }
}

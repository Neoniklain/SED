package com.unesco.core.controllerWeb;


import com.unesco.core.controller.TaskController;
import com.unesco.core.dto.task.TaskDescriptionModel;
import com.unesco.core.dto.task.TaskUserModel;
import com.unesco.core.dto.additional.ResponseStatusDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/task")
public class TaskControllerWeb {

    @Autowired
    TaskController _taskController;

    @GetMapping(value = "/list")
    public ResponseStatusDTO GetList() {
        return _taskController.GetList();
    }

    @RequestMapping(value = "/create")
    public ResponseStatusDTO Create(@RequestBody TaskDescriptionModel newTask) {
        return _taskController.Create(newTask);
    }

    @RequestMapping(value = "/answer")
    public ResponseStatusDTO Answer(@RequestBody TaskUserModel item) {
        return _taskController.Answer(item);
    }

    @RequestMapping(value = "/get/{id}")
    public ResponseStatusDTO Get(@PathVariable("id") long id) {
        return _taskController.Get(id);
    }

    @RequestMapping(value = "/update")
    public ResponseStatusDTO Update(@RequestBody TaskDescriptionModel task) {
        return _taskController.Update(task);
    }

    @RequestMapping(value = "/delete/{id}")
    public ResponseStatusDTO Delete(@PathVariable("id") long id) {
        return _taskController.Delete(id);
    }
}

package com.unesco.core.controllerWeb;


import com.unesco.core.controller.TaskController;
import com.unesco.core.dto.task.TaskDescriptionDTO;
import com.unesco.core.dto.task.TaskUserDTO;
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
    public ResponseStatusDTO getList() {
        return _taskController.getList();
    }

    @RequestMapping(value = "/create")
    public ResponseStatusDTO create(@RequestBody TaskDescriptionDTO newTask) {
        return _taskController.create(newTask);
    }

    @RequestMapping(value = "/answer")
    public ResponseStatusDTO answer(@RequestBody TaskUserDTO item) {
        return _taskController.answer(item);
    }

    @RequestMapping(value = "/changeStatus/{tu_id}/{status_id}")
    public ResponseStatusDTO changeStatus(@PathVariable("tu_id") long tu_id, @PathVariable("status_id") int status_id) {
        return _taskController.changeStatus(tu_id, status_id);
    }

    @RequestMapping(value = "/get/{id}")
    public ResponseStatusDTO get(@PathVariable("id") long id) {
        return _taskController.get(id);
    }

    @RequestMapping(value = "/update")
    public ResponseStatusDTO update(@RequestBody TaskDescriptionDTO task) {
        return _taskController.update(task);
    }

    @RequestMapping(value = "/delete/{id}")
    public ResponseStatusDTO delete(@PathVariable("id") long id) {
        return _taskController.delete(id);
    }
}

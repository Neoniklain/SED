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

    @GetMapping(value = "/getAll")
    public ResponseStatusDTO getListAll() {
        return _taskController.getListAll();
    }

    @GetMapping(value = "/listExecutor")
    public ResponseStatusDTO getListExecutor() {
        return _taskController.getListExecutor();
    }

    @GetMapping(value = "/listCreator")
    public ResponseStatusDTO getListCreator() {
        return _taskController.getListCreator();
    }

    @RequestMapping(value = "/create")
    public ResponseStatusDTO create(@RequestBody TaskDescriptionDTO newTask) {
        return _taskController.create(newTask);
    }

    @RequestMapping(value = "/answer")
    public ResponseStatusDTO answer(@RequestBody TaskUserDTO item) {
        return _taskController.changeResponseTaskUser(item);
    }

    @RequestMapping(value = "/changeStatusTaskDesc/{td_id}/{status_id}")
    public ResponseStatusDTO changeStatusTaskDesc(@PathVariable("td_id") long td_id, @PathVariable("status_id") int status_id) {
        return _taskController.changeStatusTaskDesc(td_id, status_id);
    }

    @RequestMapping(value = "/changeStatusTaskUser/{tu_id}/{status_id}")
    public ResponseStatusDTO changeStatusTaskUser(@PathVariable("tu_id") long tu_id, @PathVariable("status_id") int status_id) {
        return _taskController.changeStatusTaskUser(tu_id, status_id);
    }

    @RequestMapping(value = "/getTaskDesc/{id}")
    public ResponseStatusDTO getTaskDesc(@PathVariable("id") long id) {
        return _taskController.getTaskDesc(id);
    }

    @RequestMapping(value = "/getTaskUser/{id}")
    public ResponseStatusDTO getTaskUser(@PathVariable("id") long id) {
        return _taskController.getTaskUser(id);
    }

    @RequestMapping(value = "/updateTaskDesc")
    public ResponseStatusDTO updateTaskDesc(@RequestBody TaskDescriptionDTO task) {
        return _taskController.updateTaskDesc(task);
    }

    @RequestMapping(value = "/deleteTask/{id}")
    public ResponseStatusDTO deleteTask(@PathVariable("id") long id) {
        return _taskController.deleteTaskDesc(id);
    }

    @RequestMapping(value = "/deleteTaskUser/{id}")
    public ResponseStatusDTO deleteTaskUser(@PathVariable("id") long id) {
        return _taskController.deleteTaskUser(id);
    }
}

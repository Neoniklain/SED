package com.unesco.core.controllerWeb;


import com.unesco.core.controller.TaskController;
import com.unesco.core.entities.task.TaskDescriptionFile;
import com.unesco.core.models.task.TaskDescriptionModel;
import com.unesco.core.models.task.TaskUserModel;
import com.unesco.core.models.additional.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.apache.jasper.Constants.DEFAULT_BUFFER_SIZE;

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

    @RequestMapping(value = "/addfile/{id}")
    public ResponseStatus AddFile(@PathVariable("id") long id, @RequestParam("file") MultipartFile file) {
        return _taskController.AddFile(id,file);
    }

    @RequestMapping(path = "/download")
    public void download(HttpServletRequest request, HttpServletResponse response) throws IOException {
        TaskDescriptionFile temp = _taskController.DownloadFile(1);
        //do other stuff
        byte[] file = temp.getData();
        response.reset();
        response.setBufferSize(DEFAULT_BUFFER_SIZE);
        response.setContentType(temp.getFileType()); //or whatever file type you want to send.
        try {
            response.getOutputStream().write(file);
        } catch (IOException e) {
            // Do something
        }
    }
    @RequestMapping(value = "/getfile/{id}")
    public ResponseStatus GetFile(@PathVariable("id") long id) {
        return _taskController.GetFile(id);
    }
}

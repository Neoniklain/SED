package com.unesco.core.models;

import com.unesco.core.entities.account.User;
import com.unesco.core.entities.workflow.Task;
import com.unesco.core.models.account.UserModel;

import java.util.ArrayList;
import java.util.List;

public class TaskModel {

    private long id;
    private UserModel executor;
    private TaskDescriptionModel taskDescription;
    private String status;
    private String response;

    public TaskModel()
    {
        this.id = 0;
        this.status = "";
        this.response = "";
        this.executor = new UserModel();
        this.taskDescription = new TaskDescriptionModel();
    }

    public TaskModel(Task task)
    {
        this.id = task.getId();
        this.status = task.getStatus();
        this.response = task.getResponse();
        this.taskDescription = new TaskDescriptionModel(task.getTaskDescription());
        this.executor = new UserModel(task.getExecutor());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserModel getExecutor() {
        return executor;
    }

    public void setExecutor(UserModel executor) {
        this.executor = executor;
    }

    public TaskDescriptionModel getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(TaskDescriptionModel taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}

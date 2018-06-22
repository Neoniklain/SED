package com.unesco.core.models;

import com.unesco.core.models.account.UserDTO;

public class TaskModel {

    private long id;
    private UserDTO executor;
    private long taskDescriptionId;
    private String status;
    private String response;

    public TaskModel()
    {
        this.id = 0;
        this.status = "";
        this.response = "";
        this.executor = new UserDTO();
        this.taskDescriptionId = 0;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserDTO getExecutor() {
        return executor;
    }

    public void setExecutor(UserDTO executor) {
        this.executor = executor;
    }

    public long getTaskDescriptionId() {
        return taskDescriptionId;
    }

    public void setTaskDescriptionId(long id) {
        this.taskDescriptionId = id;
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

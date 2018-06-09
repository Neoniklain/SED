package com.unesco.core.models.task;

import com.unesco.core.models.account.UserModel;

import java.util.List;

public class TaskUserModel {

    private long id;
    private UserModel executor;
    private long taskDescriptionId;
    private String statusName;
    private int status;
    private String response;
    private List<TaskUserFileModel> files;

    public TaskUserModel()
    {
        this.id = 0;
        this.status = 0;
        this.statusName = "";
        this.response = "";
        this.executor = new UserModel();
        this.taskDescriptionId = 0;
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

    public long getTaskDescriptionId() {
        return taskDescriptionId;
    }

    public void setTaskDescriptionId(long id) {
        this.taskDescriptionId = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public List<TaskUserFileModel> getFiles() {
        return files;
    }

    public void setFiles(List<TaskUserFileModel> files) {
        this.files = files;
    }
}

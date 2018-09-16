package com.unesco.core.dto.task;

import com.unesco.core.dto.account.UserDTO;
import com.unesco.core.dto.file.FileDescriptionModel;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class TaskUserDTO {

    private long id;
    private UserDTO executor;
    private long taskDescriptionId;
    private String statusName;
    private int status;
    private String response;
    private List<FileDescriptionModel> files;
    private Timestamp dateCreate;
    private Timestamp dateRequired;

    public TaskUserDTO()
    {
        this.id = 0;
        this.status = -1;
        this.statusName = null;
        this.response = null;
        this.executor = null;
        this.taskDescriptionId = 0;
        this.files = null;
        this.dateCreate = null;
        this.dateRequired = null;
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

    public List<FileDescriptionModel> getFiles() {
        return files;
    }

    public void setFiles(List<FileDescriptionModel> files) {
        this.files = files;
    }

    public Timestamp getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Timestamp dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Timestamp getDateRequired() {
        return dateRequired;
    }

    public void setDateRequired(Timestamp dateRequired) {
        this.dateRequired = dateRequired;
    }
}

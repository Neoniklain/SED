package com.unesco.core.dto.task;

import com.unesco.core.dto.account.UserDTO;
import com.unesco.core.dto.file.FileDescriptionModel;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class TaskDescriptionDTO {

    private long id;
    private String name;
    private UserDTO creator;
    private List<TaskUserDTO> taskUsers;
    private String description;
    private int type;
    private int status;
    private String statusName;
    private List<FileDescriptionModel> files;
    private Timestamp dateCreate;
    private Timestamp dateRequired;

    public TaskDescriptionDTO()
    {
        this.id = 0;
        this.name = null;
        this.description = null;
        this.creator = null;
        this.taskUsers = null;
        this.status = -1;
        this.type = -1;
        this.statusName = "";
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserDTO getCreator() {
        return creator;
    }

    public void setCreator(UserDTO creator) {
        this.creator = creator;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public List<TaskUserDTO> getTaskUsers() {
        return taskUsers;
    }

    public void setTaskUsers(List<TaskUserDTO> taskUsers) {
        this.taskUsers = taskUsers;
    }

    public List<FileDescriptionModel> getFiles() {
        return files;
    }

    public void setFiles(List<FileDescriptionModel> files) {
        this.files = files;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

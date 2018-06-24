package com.unesco.core.models.task;

import com.unesco.core.models.account.UserDTO;
import com.unesco.core.models.file.FileDescriptionModel;

import java.util.ArrayList;
import java.util.List;

public class TaskDescriptionModel {

    private long id;
    private String name;
    private UserDTO creator;
    private List<TaskUserModel> taskUsers;
    private List<UserDTO> users;
    private String description;
    private int status;
    private String statusName;
    private List<FileDescriptionModel> files;

    public TaskDescriptionModel()
    {
        this.id = 0;
        this.name = "";
        this.description = "";
        this.creator = new UserDTO();
        this.taskUsers = new ArrayList<>();
        this.users = new ArrayList<>();
        this.status = 0;
        this.statusName = "";
        this.files = new ArrayList<>();
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

    public List<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserDTO> users) {
        this.users = users;
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

    public List<TaskUserModel> getTaskUsers() {
        return taskUsers;
    }

    public void setTaskUsers(List<TaskUserModel> taskUsers) {
        this.taskUsers = taskUsers;
    }

    public List<FileDescriptionModel> getFiles() {
        return files;
    }

    public void setFiles(List<FileDescriptionModel> files) {
        this.files = files;
    }
}

package com.unesco.core.models;

import com.unesco.core.entities.account.User;
import com.unesco.core.entities.workflow.Task;
import com.unesco.core.entities.workflow.TaskDescription;
import com.unesco.core.models.account.UserModel;

import java.util.ArrayList;
import java.util.List;

public class TaskDescriptionModel {

    private long id;
    private String name;
    private UserModel creator;
    private List<TaskModel> subTasks;
    private List<UserModel> users;
    private String description;
    private int status;
    private String statusName;

    public TaskDescriptionModel()
    {
        this.id = 0;
        this.name = "";
        this.description = "";
        this.creator = new UserModel();
        this.subTasks = new ArrayList<>();
        this.users = new ArrayList<>();
        this.status = 0;
        this.statusName = "";
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

    public UserModel getCreator() {
        return creator;
    }

    public void setCreator(UserModel creator) {
        this.creator = creator;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<TaskModel> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(List<TaskModel> subTasks) {
        this.subTasks = subTasks;
    }

    public List<UserModel> getUsers() {
        return users;
    }

    public void setUsers(List<UserModel> users) {
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
}

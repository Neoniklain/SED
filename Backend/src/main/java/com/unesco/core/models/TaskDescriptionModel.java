package com.unesco.core.models;

import com.unesco.core.models.account.UserDTO;

import java.util.ArrayList;
import java.util.List;

public class TaskDescriptionModel {

    private long id;
    private String name;
    private UserDTO creator;
    private List<TaskModel> subTasks;
    private List<UserDTO> users;
    private String description;

    public TaskDescriptionModel()
    {
        this.id = 0;
        this.name = "";
        this.description = "";
        this.creator = new UserDTO();
        this.subTasks = new ArrayList<>();
        this.users = new ArrayList<>();
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

    public List<TaskModel> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(List<TaskModel> subTasks) {
        this.subTasks = subTasks;
    }

    public List<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserDTO> users) {
        this.users = users;
    }
}

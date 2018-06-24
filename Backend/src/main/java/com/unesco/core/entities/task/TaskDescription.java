package com.unesco.core.entities.task;

import com.unesco.core.entities.account.UserEntity;
import com.unesco.core.entities.file.FileDescription;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="un_task_description")
public class TaskDescription {
    @Id
    @SequenceGenerator(name = "taskDescriptionSequenceGen", sequenceName = "taskDescriptionSequenceGen", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "taskDescriptionSequenceGen")
    private long id;
    private String name;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity creator;

    /*  FetchType.
        LAZY = Загрузит при обращении к полю этого класса
        EAGER = Загрузит сразу, при создании объекта этого класса
    */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "taskDescription")
    private List<TaskUser> taskUsers;

    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
    @JoinTable(name = "un_TD_F",
            joinColumns = {@JoinColumn(name = "taskDescription_id")},
            inverseJoinColumns = {@JoinColumn(name = "fileDescription_id")})
    private Set<FileDescription> files;

    private int status;
    private String description;

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

    public UserEntity getCreator() {
        return creator;
    }

    public void setCreator(UserEntity creator) {
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

    public List<TaskUser> getTaskUsers() {
        return taskUsers;
    }

    public void setTaskUsers(List<TaskUser> taskUsers) {
        this.taskUsers = taskUsers;
    }

    public Set<FileDescription> getFiles() {
        return files;
    }

    public void setFiles(Set<FileDescription> files) {
        this.files = files;
    }
}

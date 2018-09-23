package com.unesco.core.entities.task;

import com.unesco.core.entities.account.UserEntity;
import com.unesco.core.entities.file.FileDescription;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name="un_task_user")
public class TaskUser {
    @Id
    @SequenceGenerator(name = "taskSequenceGen", sequenceName = "taskSequenceGen", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "taskSequenceGen")
    private long id;

    @ManyToOne
    @JoinColumn(name = "un_task_description", referencedColumnName = "id")
    private TaskDescription taskDescription;

    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
    @JoinTable(name = "un_TU_F",
            joinColumns = {@JoinColumn(name = "taskUser_id")},
            inverseJoinColumns = {@JoinColumn(name = "fileDescription_id")})
    private Set<FileDescription> files;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity executor;
    private int status;
    private String response;
    private Timestamp dateCreate;
    private Timestamp dateRequired;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public TaskDescription getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(TaskDescription taskDescription) {
        this.taskDescription = taskDescription;
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

    public UserEntity getExecutor() {
        return executor;
    }

    public void setExecutor(UserEntity executor) {
        this.executor = executor;
    }

    public Set<FileDescription> getFiles() {
        return files;
    }

    public void setFiles(Set<FileDescription> files) {
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

package com.unesco.core.entities.workflow;

import com.unesco.core.entities.account.User;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="task")
public class Task {
    @Id
    @SequenceGenerator(name = "taskSequenceGen", sequenceName = "taskSequenceGen", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "taskSequenceGen")
    private long id;

    @ManyToOne
    @JoinColumn(name = "task_description", referencedColumnName = "id")
    private TaskDescription taskDescription;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User executor;
    private String status;
    private String response;

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

    public User getExecutor() {
        return executor;
    }

    public void setExecutor(User executor) {
        this.executor = executor;
    }
}

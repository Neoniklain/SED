package com.unesco.core.entities.task;

import javax.persistence.*;

@Entity
@Table(name = "un_task_description_file")
public class TaskDescriptionFile {
    @Id
    @SequenceGenerator(name = "taskDescriptionFileSequenceGen", sequenceName = "taskDescriptionFileSequenceGen", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "taskDescriptionFileSequenceGen")
    private long id;
    private long taskDescriptionId;

    private String fileName;
    private String fileType;
    private byte[] data;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public long getTaskDescriptionId() {
        return taskDescriptionId;
    }

    public void setTaskDescriptionId(long taskDescriptionId) {
        this.taskDescriptionId = taskDescriptionId;
    }
}
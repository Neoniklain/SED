package com.unesco.core.models.task;

public class TaskUserFileModel {
    private long id;
    private long taskUserId;

    private String fileName;
    private String fileType;
    private byte[] data;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTaskUserId() {
        return taskUserId;
    }

    public void setTaskUserId(long taskUserId) {
        this.taskUserId = taskUserId;
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
}

package com.unesco.core.models.file;


public class FileByteCodeModel {
    private long id;
    private byte[] data;

    private FileDescriptionModel fileDescription;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public FileDescriptionModel getFileDescription() {
        return fileDescription;
    }

    public void setFileDescription(FileDescriptionModel fileDescription) {
        this.fileDescription = fileDescription;
    }
}

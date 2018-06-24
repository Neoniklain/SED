package com.unesco.core.entities.file;

import javax.persistence.*;

@Entity
@Table(name="un_file_description")
public class FileDescription {
    @Id
    @SequenceGenerator(name = "FileDescriptionSequenceGen", sequenceName = "FileDescriptionSequenceGen", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FileDescriptionSequenceGen")
    private long id;
    private String fileName;
    private String fileType;


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
}

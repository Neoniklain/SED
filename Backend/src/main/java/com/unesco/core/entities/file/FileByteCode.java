package com.unesco.core.entities.file;

import javax.persistence.*;

@Entity
@Table(name="un_file_byte_code")
public class FileByteCode {
    @Id
    @SequenceGenerator(name = "FileByteCodeSequenceGen", sequenceName = "FileByteCodeSequenceGen", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FileByteCodeSequenceGen")
    private long id;
    private byte[] data;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_description_id")
    private FileDescription fileDescription;

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

    public FileDescription getFileDescription() {
        return fileDescription;
    }

    public void setFileDescription(FileDescription fileDescription) {
        this.fileDescription = fileDescription;
    }
}

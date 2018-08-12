package com.unesco.core.entities.account;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="un_accessRight")
public class AccessRightEntity {

    @Id
    @SequenceGenerator(name = "accessRightSequenceGen", sequenceName = "accessRightSequenceGen", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accessRightSequenceGen")
    private long id;

    @Column(unique=true)
    @NotNull
    private String name;

    private String header;

    private String caption;

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

    public String getHeader() {
        return header;
    }
    public void setHeader(String header) {
        this.header = header;
    }

    public String getCaption() {
        return caption;
    }
    public void setCaption(String caption) {
        this.caption = caption;
    }
}

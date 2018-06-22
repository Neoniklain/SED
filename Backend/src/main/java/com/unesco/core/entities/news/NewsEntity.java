package com.unesco.core.entities.news;


import com.unesco.core.entities.account.UserEntity;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="un_news")
public class NewsEntity {
    @Id
    @SequenceGenerator(name = "newsSequenceGen", sequenceName = "newsSequenceGen", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "newsSequenceGen")
    private long id;
    private String header;
    @ManyToOne
    private UserEntity author;
    @Type(type = "org.hibernate.type.TextType")
    private String content;
    private String tags;
    @Type(type="org.hibernate.type.BinaryType")
    private byte[] image;
    private Date date;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getHeader() {
        return header;
    }
    public void setHeader(String header) {
        this.header = header;
    }

    public UserEntity getAuthor() {
        return author;
    }
    public void setAuthor(UserEntity author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public String getTags() {
        return tags;
    }
    public void setTags(String tags) {
        this.tags = tags;
    }

    public byte[] getImage() {
        return image;
    }
    public void setImage(byte[] image) {
        this.image = image;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public NewsEntity(){}
    public NewsEntity(String header, String content, Date date, String tags, UserEntity author)
    {
        this.header = header;
        this.content = content;
        this.date = date;
        this.tags = tags;
        this.author = author;
    }
}

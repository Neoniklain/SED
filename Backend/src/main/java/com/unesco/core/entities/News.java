package com.unesco.core.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="news")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String header;
    @ManyToOne
    private User author;
    private String content;
    private String tags;
    private String image;
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

    public User getAuthor() {
        return author;
    }
    public void setAuthor(User author) {
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

    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public News(){}
    public News(String header,String content,Date date,String tags,User author)
    {
        this.header = header;
        this.content = content;
        this.date = date;
        this.tags = tags;
        this.author = author;
    }
}

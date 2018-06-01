package com.unesco.core.models.news;

import com.unesco.core.entities.account.User;
import com.unesco.core.models.account.UserModel;

import java.util.Date;

public class NewsModel {
    private long id;
    private String header;
    private UserModel author;
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

    public UserModel getAuthor() {
        return author;
    }
    public void setAuthor(UserModel author) {
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

}

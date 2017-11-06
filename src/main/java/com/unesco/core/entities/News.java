package com.unesco.core.entities;

import java.util.Date;

public class News {
    private String header;
    private String author;
    private String content;
    private String tags;
    private Date date;

    public String getHeader() {
        return header;
    }
    public void setHeader(String header) {
        this.header = header;
    }

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
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

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public News(){}
    public News(String header,String content,Date date)
    {
        this.header = header;
        this.content = content;
        this.date = date;
    }
}

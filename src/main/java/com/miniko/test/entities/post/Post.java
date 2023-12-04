package com.miniko.test.entities.post;

import jakarta.persistence.*;

import java.util.Date;

@Entity(name = "posts")
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String userId;

    private String description;

    private Date date;

    private String fileLink;

    public Post() {

    }

    public Post(String userId, String description, Date date, String fileLink) {
        this.userId = userId;
        this.description = description;
        this.date = date;
        this.fileLink = fileLink;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getFileLink() {
        return fileLink;
    }

    public void setFileLink(String fileLink) {
        this.fileLink = fileLink;
    }
}

package com.miniko.test.entities.post;

import jakarta.persistence.*;

import java.util.Date;

@Entity(name = "posts")
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String user_id;

    private String description;

    private Date date;

    private String file_link;

    public Post() {

    }

    public Post(String user_id, String description, Date date, String file_link) {
        this.user_id = user_id;
        this.description = description;
        this.date = date;
        this.file_link = file_link;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return user_id;
    }

    public void setUserId(String user_id) {
        this.user_id = user_id;
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
        return file_link;
    }

    public void setFileLink(String file_link) {
        this.file_link = file_link;
    }
}

package com.miniko.test.entities.post;

import jakarta.persistence.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;

@Entity(name = "posts")
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String userId;

    private String description;

    private Date date;

    public Post() {

    }

    public Post(String userId, String description, Date date) {
        this.userId = userId;
        this.description = description;
        this.date = date;
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

    public String getFile() throws IOException {
        File directory = new File("src/main/resources/static/posts/");

        Optional<File> file = Arrays.stream(directory.listFiles())
                .filter(file1 -> file1.getName().startsWith(this.id))
                .findFirst();

        if(file.get().exists()) {
            byte[] fileContent = Files.readAllBytes(file.get().toPath());
            String base64 = Base64.getEncoder().encodeToString(fileContent);

            return base64;
        }

        return null;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


}

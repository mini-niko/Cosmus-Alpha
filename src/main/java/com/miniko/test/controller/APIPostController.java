package com.miniko.test.controller;

import com.miniko.test.entities.post.Post;
import com.miniko.test.service.PostService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/posts")
public class APIPostController {

    private final PostService postService;

    @Autowired
    public APIPostController(PostService postService) {
        this.postService = postService;
    }

    private List<String> imageExtensions = new ArrayList<String>() {{
        add(".jpg");
        add(".jpeg");
        add(".png");
        add(".webp");
        add(".gif");
    }};

    @PostMapping("create")
    public ResponseEntity createPost(
            @RequestPart("userId") @NotBlank String userId,
            @RequestPart("description") String description,
            @RequestPart("file") MultipartFile file
    ) {
        Post post = new Post(userId, description, new Date());
        postService.createPost(post);

        try {
            String fileName = file.getOriginalFilename();
            String fileExtension = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();

            if(!imageExtensions.contains(fileExtension))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

            String newFileName = post.getId() + fileExtension;
            Files.write(Path.of("src/main/resources/static/posts/" + newFileName), file.getBytes());

            return ResponseEntity.status(HttpStatus.CREATED).body(post);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }
}

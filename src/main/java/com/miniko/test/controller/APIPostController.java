package com.miniko.test.controller;

import com.miniko.test.entities.post.Post;
import com.miniko.test.entities.post.PostCreateDTO;
import com.miniko.test.entities.user.User;
import com.miniko.test.service.PostService;
import com.miniko.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("api/posts")
public class APIPostController {
    private final PostService postService;
    private final UserService userService;

    @Autowired
    public APIPostController(PostService postService, UserService userService) {
        this.postService = postService; this.userService = userService;
    }

    @PostMapping("create")
    public ResponseEntity createPost(@RequestBody PostCreateDTO postCreateDTO) {
        if(postCreateDTO.userId() == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Expected userId, but is null");
        if(postCreateDTO.file() == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Expected file, but is null");

        Optional<User> user = userService.findUserById(postCreateDTO.userId());
        if(user.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found user with Id");

        boolean validImage = postService.isValidImage(postCreateDTO.file());
        if(!validImage) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid URL");

        Post post = new Post(postCreateDTO.userId(), postCreateDTO.description(), new Date(), postCreateDTO.file());
        postService.createPost(post);

        return ResponseEntity.ok(post);
    }

    @GetMapping("valid-image")
    public boolean validImage(@RequestParam String imageLink) {
        return postService.isValidImage(imageLink);
    }
}

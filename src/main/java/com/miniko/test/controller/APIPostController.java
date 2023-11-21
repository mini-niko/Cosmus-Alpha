package com.miniko.test.controller;

import com.miniko.test.entities.post.Post;
import com.miniko.test.entities.post.PostDTO;
import com.miniko.test.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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

    @PostMapping("create")
    public ResponseEntity createPost(@RequestBody PostDTO postDTO) throws IOException {
        if(postDTO.userId().isEmpty() || postDTO.file().isEmpty()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Parameters");

        Post post = new Post();

        post.setUserId(postDTO.userId());
        post.setDescription(postDTO.description());
        post.setDate(new Date());
        post.setImage(postDTO.file().getBytes());

        postService.createPost(post);

        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }

    @GetMapping("get-all")
    public ResponseEntity getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }
}

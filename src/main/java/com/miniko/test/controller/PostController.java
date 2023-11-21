package com.miniko.test.controller;

import com.miniko.test.entities.post.Post;
import com.miniko.test.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//API para os posts
@RestController
@RequestMapping("api/posts")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

//    Desativado por questões de segurança (retorna todos os posts existentes)
//    @GetMapping
//    public List<Post> getAllPosts() {
//        return postService.getAllPosts();
//    }

    @GetMapping("/find-by-user")
    public List<Post> findPostByUser(@RequestParam Long userId) {
        return postService.getAllPostByUserId(userId);
    }
}

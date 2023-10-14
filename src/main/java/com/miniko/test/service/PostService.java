package com.miniko.test.service;

import com.miniko.test.entities.Post;
import com.miniko.test.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public Post updateAccount(Long id, Post updatePost) {
        Optional<Post> existingPost = postRepository.findById(id);
        if (existingPost.isPresent()) {
            Post post = existingPost.get();
            post.setTitle(updatePost.getTitle());
            post.setComments(updatePost.getComments());
            post.setAccountId(updatePost.getAccountId());
            return postRepository.save(post);
        }
        return null;
    }

    public boolean deleteAccount(Long id) {
        Optional<Post> existingPost = postRepository.findById(id);
        if (existingPost.isPresent()) {
            postRepository.delete(existingPost.get());
            return true;
        }
        return false;
    }

    public List<Post> getAllPostByAccountId(Long id) {
        return postRepository.findByAccountID(id);
    }
}

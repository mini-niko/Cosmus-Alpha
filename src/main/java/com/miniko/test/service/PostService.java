package com.miniko.test.service;

import com.miniko.test.entities.post.Post;
import com.miniko.test.entities.post.PostViewDTO;
import com.miniko.test.repository.PostRepository;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;

//Funções que trabalham com os posts no repositório (banco de dados)
@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    //Retorna todos os posts
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }
    //Retorna o post com um id específico
    public Optional<Post> getPostById(String id) {
        return postRepository.findById(id);
    }
    //Cria um post no repositório
    public Post createPost(Post post) {
        return postRepository.save(post);
    }
    //Atualiza um post no repositório
    public Post updateUser(String id, Post updatePost) {
        Optional<Post> existingPost = postRepository.findById(id);
        if (existingPost.isPresent()) {
            Post post = existingPost.get();
            post.setDescription(updatePost.getDescription());
            post.setUserId(updatePost.getUserId());
            return postRepository.save(post);
        }
        return null;
    }

    //Deleta um post no repositório
    public boolean deleteUser(String id) {
        Optional<Post> existingPost = postRepository.findById(id);
        if (existingPost.isPresent()) {
            postRepository.delete(existingPost.get());
            return true;
        }
        return false;
    }

    //Retorna todos os posts com o mesmo id de conta (mesmo autor)
    public List<Post> getAllPostByUserId(Long id) {
        return postRepository.findByUserId(id);
    }

    public List<PostViewDTO> getAllPostsViewDTO() {
        return postRepository.getAllPostsViewDTO();
    }

    //Verifica se o link enviado é uma imagem válido
    public boolean isValidImage(String imageLink) {
        final String[] ALLOWED_IMAGE_SCHEMES = {"http", "https"};
        final String[] IMAGE_MIME_TYPES = {"image/jpeg", "image/png", "image/gif", "image/bmp"};

        UrlValidator urlValidator = new UrlValidator(ALLOWED_IMAGE_SCHEMES);

        if (!urlValidator.isValid(imageLink)) return false;

        try {
            URL imageUrl = new URL(imageLink);
            Image image = ImageIO.read(imageUrl);

            if(image != null) return true;
            else return false;

        } catch (IOException e) {
            return false;
        }
    }
}

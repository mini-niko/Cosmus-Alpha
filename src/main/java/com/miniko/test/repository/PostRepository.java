package com.miniko.test.repository;

import com.miniko.test.entities.post.Post;
import com.miniko.test.entities.post.PostViewDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

//Responsável por fazer a conexão do Service ao repositório (banco de dados)
public interface PostRepository extends JpaRepository<Post, String> {
    //Query em SQL para encontrar todos os posts com o mesmo id de conta, como parâmetro
    @Query("SELECT p FROM posts p WHERE p.userId = :userId")
    List<Post> findByUserId(@Param("userId") Long userId);

    //Query em SQL para encontrar todos os posts, em formato de PostViewDTO
    @Query("SELECT u.name, u.avatar, p.description, p.fileLink, p.date FROM posts p LEFT JOIN users u ON p.userId = u.id")
    List<PostViewDTO> getAllPostsViewDTO();
}

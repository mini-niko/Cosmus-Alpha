package com.miniko.test.repository;

import com.miniko.test.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

//Responsável por fazer a conexão do Service ao repositório (banco de dados)
public interface PostRepository extends JpaRepository<Post, String> {
    //Query em SQL para encontrar todos os posts com o mesmo id de conta, como parâmetro
    @Query("SELECT p FROM posts p WHERE p.userId = :userId")
    List<Post> findByUserId(@Param("userId") Long userId);
}

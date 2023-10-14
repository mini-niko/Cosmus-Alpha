package com.miniko.test.repository;

import com.miniko.test.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p FROM Post p WHERE p.accountId = :accountId")
    List<Post> findByAccountID(@Param("accountId") Long accountId);
}

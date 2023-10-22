package com.miniko.test.repository;

import com.miniko.test.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

//Responsável por fazer a conexão do Service ao repositório (banco de dados)
public interface UserRepository extends JpaRepository<User, String> {
    //Query em SQL para encontrar uma conta com um email de parâmetro
    @Query("SELECT u FROM users u WHERE u.email = :email")
    User findUserByEmail(@Param("email") String email);

    //Query em SQL para encontrar uma conta com um nome de parâmetro
    @Query("SELECT u FROM users u WHERE u.name = :name")
    User findUserByName(@Param("name") String name);

    //Query em SQL para encontrar uma conta com um nome e senha de parâmetro
    @Query("SELECT u FROM users u WHERE u.email = :email AND u.password = :password")
    User findUserByEmailAndPassword(@Param("email") String email, @Param("password") String password);
}

package com.miniko.test.repository;

import com.miniko.test.entities.Account;
import com.miniko.test.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

//Responsável por fazer a conexão do Service ao repositório (banco de dados)
public interface AccountRepository extends JpaRepository<Account, Long> {
    //Query em SQL para encontrar uma conta com um email de parâmetro
    @Query("SELECT a FROM Account a WHERE a.email = :email")
    Account findAccountByEmail(@Param("email") String email);

    //Query em SQL para encontrar uma conta com um nome de parâmetro
    @Query("SELECT a FROM Account a WHERE a.name = :name")
    Account findAccountByName(@Param("name") String name);

    //Query em SQL para encontrar uma conta com um nome e senha de parâmetro
    @Query("SELECT a FROM Account a WHERE a.email = :email AND a.password = :password")
    Account findAccountByEmailAndPassword(@Param("email") String email, @Param("password") String password);
}

package com.miniko.test.service;

import com.miniko.test.entities.user.User;
import com.miniko.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//Funções que trabalham com as contas do repositório (banco de dados)
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByEmail(email);

        if (user.isPresent()) {
            return user.get();
        }
        else {
            throw new UsernameNotFoundException("Not found user with name: " + email);
        }
    }

    //Retorna todas as contas
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    //Cria uma conta no repositório
    public void createUser(User user) {
        userRepository.save(user);
    }

    //Atualiza uma conta no repositório
    public void updateUser(String id, User updatedUser) {
        Optional<User> existingUser = userRepository.findById(id);

        if (existingUser.isPresent()) {
            User user = existingUser.get();
            System.out.println(user.getId());
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            user.setPassword(updatedUser.getPassword());
            user.setAvatar(updatedUser.getAvatar());

            userRepository.save(user);
        }
    }

    //Deleta uma conta no repositório
    public void deleteUser(String id) {
        Optional<User> existingUser = userRepository.findById(id);

        existingUser.ifPresent(userRepository::delete);
    }

    //Retorna uma conta por seu id
    public Optional<User> findUserById(String id) {
        return userRepository.findById(id);
    }

    //Encontra uma conta por seu nome
    public Optional<User> findUserByName(String name) {
        return userRepository.findUserByName(name);
    }

    //Encontra uma conta por seu email
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    //Encontra uma conta por seu nome e senha
    public Optional<User> findUserByEmailAndPassword(String email, String password) throws UsernameNotFoundException {
        return userRepository.findUserByEmailAndPassword(email, password);
    }
}

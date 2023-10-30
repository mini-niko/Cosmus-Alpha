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
        User user = userRepository.findUserByEmail(email);

        if (user == null) throw new UsernameNotFoundException("Not found user with email: " + email);

        return user;
    }

    //Retorna todas as contas
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    //Retorna uma conta com um id em específico
    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }
    //Cria uma conta no repositório
    public User createUser(User user) {
        return userRepository.save(user);
    }
    //Atualiza uma conta no repositório
    public User updateUser(String id, User updatedUser) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            user.setPassword(updatedUser.getPassword());
            user.setAvatar(updatedUser.getAvatar());
            return userRepository.save(user);
        }
        return null;
    }
    //Deleta uma conta no repositório
    public boolean deleteUser(String id) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            userRepository.delete(existingUser.get());
            return true;
        }
        return false;
    }
    //Encontra uma conta por seu email
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }
    //Encontra uma conta por seu nome
    public User findUserByName(String name) {
        return userRepository.findUserByName(name);
    }
    //Encontra uma conta por seu nome e senha
    public User findUserByEmailAndPassword(String email, String password) throws UsernameNotFoundException {
        return userRepository.findUserByEmailAndPassword(email, password);
    }
}

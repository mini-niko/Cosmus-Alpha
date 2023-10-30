package com.miniko.test.entities.user;

public record UserDTO(String id, String name, String email, String password, UserRole role, String avatar, String token) {
    public UserDTO(User user, String token) {
        this(user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getRole(), user.getAvatar(), token);
    }
}

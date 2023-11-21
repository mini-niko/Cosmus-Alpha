package com.miniko.test.entities.post;

import org.springframework.web.multipart.MultipartFile;

public record PostDTO(String userId, String description, MultipartFile file) {
}

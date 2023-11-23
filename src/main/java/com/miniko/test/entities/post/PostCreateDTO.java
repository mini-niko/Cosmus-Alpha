package com.miniko.test.entities.post;

import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

public record PostCreateDTO(@RequestPart String userId, @RequestPart String description, @RequestPart MultipartFile file) {

}

package com.miniko.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.miniko.test.controller.APIPostController;
import com.miniko.test.entities.post.PostCreateDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PostTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    APIPostController apiPostController;

    @Test
    public void createPost() throws JsonProcessingException {
        String imageLink = "https:i.postimg.cc/2ynZ85NS/695cc0c3754f46ce91b1598312b1d1c377b1b2d2-00.jpg";
        String invalidLink = "https:www.youtube.com/watch?v=yoD0CxhVoPE";

        String userIdNullPostCreateDTO = new ObjectMapper().writeValueAsString(
                new PostCreateDTO(
                        null,
                        "",
                        imageLink
                )
        );

        String userIdInvalidPostCreateDTO = new ObjectMapper().writeValueAsString(
                new PostCreateDTO(
                    "123456",
                    "",
                    imageLink
                )
        );

        String fileNullPostCreateDTO = new ObjectMapper().writeValueAsString(
                new PostCreateDTO(
                        "54d53ff7-1ef5-4bf2-b7fd-0eadcb326dcf",
                        "",
                        null
                )
        );

        String fileInvalidPostCreateDTO = new ObjectMapper().writeValueAsString(
                new PostCreateDTO(
                        "54d53ff7-1ef5-4bf2-b7fd-0eadcb326dcf",
                        "",
                        invalidLink
                )
        );

        String validPostCreateDTO = new ObjectMapper().writeValueAsString(
                new PostCreateDTO(
                        "54d53ff7-1ef5-4bf2-b7fd-0eadcb326dcf",
                        "",
                        imageLink
                )
        );
    }
}

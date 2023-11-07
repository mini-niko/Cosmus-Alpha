package com.miniko.test.controller;

import com.miniko.test.entities.user.*;
import com.miniko.test.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class WebController {

    private final UserService userService;

    @Autowired
    public APIUserController apiUserController;

    @Autowired
    public WebController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = { "", "/" })
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public ModelAndView loginGet(HttpSession httpSession) {
        ModelAndView mv = new ModelAndView();

        if(httpSession.getAttribute("user") == null) {
            mv.addObject("user", new User());
            mv.setViewName("login");
        }
        else {
            mv.setViewName("redirect:/home");
        }

        return mv;
    }

    @GetMapping("/registry")
    public ModelAndView registryGet(HttpSession httpSession) {
        ModelAndView mv = new ModelAndView();

        if(httpSession.getAttribute("user") == null) {
            mv.addObject("user", new User());
            mv.setViewName("registry");
        }
        else {
            mv.setViewName("redirect:/home");
        }

        return mv;
    }

    @GetMapping("/home")
    public ModelAndView home(HttpSession httpSession) throws IOException {

        ModelAndView mv = new ModelAndView();
        mv.setViewName("home");
        mv.addObject("user", httpSession.getAttribute("user"));


        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath:/static/assets/img/avatar/*");

        List<String> images = new ArrayList<>();
        for (Resource resource : resources) {
            images.add(resource.getFilename());
        }
        mv.addObject("imagesName", images);

        return mv;
    }

    @PostMapping("/login")
    public ModelAndView loginPost(LoginDTO userLogin, HttpSession httpSession) {
        ModelAndView mv = new ModelAndView();

        ResponseEntity responseEntity = apiUserController.login(userLogin, httpSession);

        if(responseEntity.getStatusCode() == HttpStatus.OK) {
            UserDTO userDTO = (UserDTO) responseEntity.getBody();

            httpSession.setAttribute("user", userDTO);

            mv.setViewName("redirect:/home");
        }
        else {
            mv.addObject("error", responseEntity.getBody());
            mv.addObject("user", new User());
        }

        return mv;
    }

    @PostMapping("/registry")
    public ModelAndView registryPost(RegisterDTO userRegistry, HttpSession httpSession) {
        ModelAndView mv = new ModelAndView();

        ResponseEntity responseEntity = apiUserController.register(userRegistry, httpSession);

        if(responseEntity.getStatusCode() == HttpStatus.CREATED) {
            UserDTO userDTO = (UserDTO) responseEntity.getBody();

            httpSession.setAttribute("user", userDTO);

            mv.setViewName("redirect:/home");
        }
        else {
            mv.addObject("error", responseEntity.getBody());
            mv.addObject("user", new User());
        }

        return mv;
    }

    @PostMapping("/change-avatar")
    public ResponseEntity changeAvatar(@RequestBody AvatarDTO avatar, HttpSession httpSession) {
        UserDTO oldUserDTO = (UserDTO) httpSession.getAttribute("user");
        ModelAndView mv = new ModelAndView();

        ResponseEntity responseEntity = apiUserController.changeAvatar(avatar, httpSession);

        if(responseEntity.getStatusCode() == HttpStatus.OK) {
            User user = (User) responseEntity.getBody();
            UserDTO NewUserDTO = new UserDTO(user, oldUserDTO.token());

            httpSession.setAttribute("user", NewUserDTO);
        }

        return responseEntity;
    }
}
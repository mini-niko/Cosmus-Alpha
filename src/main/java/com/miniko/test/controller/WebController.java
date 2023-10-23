package com.miniko.test.controller;

import com.miniko.test.entities.user.LoginDTO;
import com.miniko.test.entities.user.RegisterDTO;
import com.miniko.test.entities.user.User;
import com.miniko.test.entities.user.UserDTO;
import com.miniko.test.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebController {

    private final UserService userService;

    @Autowired
    public APIUserController apiUserController;

    @Autowired
    public WebController(UserService userService) {
        this.userService = userService;
    }

//    @GetMapping("/index")
//    public String index() {
//        return "index";
//    }

    @GetMapping(value = { "", "/" })
    public String redirect() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public ModelAndView loginGet(HttpSession httpSession) {
        ModelAndView mv = new ModelAndView();

        if(httpSession.getAttribute("userToken") == null) {
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

        if(httpSession.getAttribute("userToken") == null) {
            mv.addObject("user", new User());
            mv.setViewName("registry");
        }
        else {
            mv.setViewName("redirect:/home");
        }

        return mv;
    }

    @GetMapping("/home")
    public ModelAndView home(UserDTO userData, HttpSession httpSession) {

        ModelAndView mv = new ModelAndView();
        mv.setViewName("home");
        mv.addObject("userName", httpSession.getAttribute("userName"));

        return mv;
    }

    @PostMapping("/login")
    public ModelAndView loginPost(LoginDTO userLogin, HttpSession httpSession) {
        ModelAndView mv = new ModelAndView();

        ResponseEntity responseEntity = apiUserController.login(userLogin);

        if(responseEntity.getStatusCode() == HttpStatus.OK) {
            UserDTO userDTO = (UserDTO) responseEntity.getBody();

            httpSession.setAttribute("userToken", userDTO.token());
            httpSession.setAttribute("userName", userDTO.name());

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

        ResponseEntity responseEntity = apiUserController.register(userRegistry);

        if(responseEntity.getStatusCode() == HttpStatus.CREATED) {
            UserDTO userDTO = (UserDTO) responseEntity.getBody();

            httpSession.setAttribute("userToken", userDTO.token());
            httpSession.setAttribute("userName", userDTO.name());

            mv.setViewName("redirect:/home");
        }
        else {
            mv.addObject("error", responseEntity.getBody());
            mv.addObject("user", new User());
        }

        return mv;
    }
}

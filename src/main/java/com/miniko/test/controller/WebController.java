package com.miniko.test.controller;

import com.miniko.test.entities.Account;
import com.miniko.test.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Responsável por renderizar as páginas do site
@Controller
public class WebController {
    @GetMapping("/login")
    public String renderLogin(Model model) {
        return "login";
    }
    @GetMapping("/registry")
    public String renderRegistry(Model model) {
        return "registry";
    }

}

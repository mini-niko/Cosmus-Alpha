package com.miniko.test.controller;

import com.miniko.test.entities.Account;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebController {

    AccountController accountController;

    @GetMapping("/login")
    public ModelAndView loginGet() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("account", new Account());
        mv.setViewName("login");
        return mv;
    }

    @GetMapping("/registry")
    public ModelAndView registryGet() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("account", new Account());
        mv.setViewName("registry");
        return mv;
    }

    @PostMapping("/login")
    public ModelAndView loginPost(Account account) {
        ModelAndView mv = new ModelAndView();

        return mv;
    }

    @PostMapping("/registry")
    public ModelAndView registryPost(@Valid Account account, BindingResult bindingResult) {
        ModelAndView mv = new ModelAndView();

        if(!bindingResult.hasErrors()) {
            accountController.createAccount(account);

            mv.addObject(account);
            mv.setViewName("index");
        }

        return mv;
    }
}

package com.miniko.test.controller;

import com.miniko.test.entities.Account;
import com.miniko.test.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.validation.Validator;

@RestController
@RequestMapping("api/accounts")
public class AccountController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/email-used")
    public boolean verifyEmailUsed(@RequestParam String email) {
        return accountService.findAccountByEmail(email) != null;
    }

    @PostMapping("/create")
    public Account createAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }


}

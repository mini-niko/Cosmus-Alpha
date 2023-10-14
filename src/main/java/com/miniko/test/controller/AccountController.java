package com.miniko.test.controller;

import com.miniko.test.entities.Account;
import com.miniko.test.entities.AccountRegistry;
import com.miniko.test.repository.AccountRepository;
import com.miniko.test.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/accounts")
public class AccountController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public List<Account> getAllAccount() {
        return accountService.getAllAccounts();
    }

    @PostMapping("/registry")
    public Account createAccount(@RequestBody AccountRegistry accountRegistry) {
        Account account = new Account(
                accountRegistry.getName(),
                accountRegistry.getEmail(),
                accountRegistry.getPassword()
        );

        return accountService.createAccount(account);
    }

    @PostMapping("/login")
    public Account account(@RequestBody Account account) {
        return new Account();
    }

}

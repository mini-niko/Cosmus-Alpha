package com.miniko.test.service;

import com.miniko.test.entities.Account;
import com.miniko.test.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Optional<Account> getAccountById(Long id) {
        return accountRepository.findById(id);
    }

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public Account updateAccount(Long id, Account updatedaccount) {
        Optional<Account> existingaccount = accountRepository.findById(id);
        if (existingaccount.isPresent()) {
            Account account = existingaccount.get();
            account.setName(updatedaccount.getName());
            account.setEmail(updatedaccount.getEmail());
            account.setPassword(updatedaccount.getPassword());
            return accountRepository.save(account);
        }
        return null;
    }

    public boolean deleteAccount(Long id) {
        Optional<Account> existingAccount = accountRepository.findById(id);
        if (existingAccount.isPresent()) {
            accountRepository.delete(existingAccount.get());
            return true;
        }
        return false;
    }

}

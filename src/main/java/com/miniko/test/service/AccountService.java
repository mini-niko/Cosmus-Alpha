package com.miniko.test.service;

import com.miniko.test.entities.Account;
import com.miniko.test.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//Funções que trabalham com as contas do repositório (banco de dados)
@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    //Retorna todas as contas
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }
    //Retorna uma conta com um id em específico
    public Optional<Account> getAccountById(Long id) {
        return accountRepository.findById(id);
    }
    //Cria uma conta no repositório
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }
    //Atualiza uma conta no repositório
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
    //Deleta uma conta no repositório
    public boolean deleteAccount(Long id) {
        Optional<Account> existingAccount = accountRepository.findById(id);
        if (existingAccount.isPresent()) {
            accountRepository.delete(existingAccount.get());
            return true;
        }
        return false;
    }
    //Encontra uma conta por seu email
    public Account findAccountByEmail(String email) {
        Account account = accountRepository.findAccountByEmail(email);
        return account;
    }
    //Encontra uma conta por seu nome
    public Account findAccountByName(String name) {
        return accountRepository.findAccountsByName(name);
    }
}

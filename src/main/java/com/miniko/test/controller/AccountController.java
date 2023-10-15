package com.miniko.test.controller;

import com.miniko.test.classes.Methods;
import com.miniko.test.entities.Account;
import com.miniko.test.entities.AccountRegistry;
import com.miniko.test.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//API das contas
@RestController
@RequestMapping("api/accounts")
public class AccountController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

//    Desativado por questões de segurança (retorna todas as contas, com seus ids, nomes, emails e senhas)
//    @GetMapping
//    public List<Account> getAllAccount() {
//        return accountService.getAllAccounts();
//    }

    //Request perguntando se um email já foi utilizado
    @GetMapping("/email-used")
    public boolean haveAccountWithEmail(@RequestParam String email) {
        return accountService.findAccountByEmail(email) != null ? true : false;
    }

    //Request de registro de uma conta
    @PostMapping("/registry")
    public Account createAccount(@RequestBody AccountRegistry accountRegistry) {
        boolean status = verifyAccountRegistry(accountRegistry);

        if(status) {
            Account account = new Account(
                    accountRegistry.getName(),
                    accountRegistry.getEmail(),
                    accountRegistry.getPassword()
            );

            return accountService.createAccount(account);
        }
        else {
            return new Account();
        }
    }
    //Request de login de uma conta
    @GetMapping("/login")
    public Account account(@RequestParam String identificator, String password) {
        Account account = findAccountLogin(identificator);
        return
            account != null && account.getPassword().equals(password) ?
                account :
                new Account();
    }
    //Valida uma conta a ser registrada
    private boolean verifyAccountRegistry(AccountRegistry accountRegistry) {

        Methods methods = new Methods();

        //Verifica se há um nome inserido e se ele possui entre 4 a 32 caracteres
        if(accountRegistry.getName().isEmpty() || accountRegistry.getName().length() > 32 || accountRegistry.getName().length() < 4) return false;

        //Verifica se há um email, se esse email é válido e se já foi utilizado
        if(accountRegistry.getEmail().isEmpty() || !methods.isValidEmail(accountRegistry.getEmail()) || accountService.findAccountByEmail(accountRegistry.getEmail()) != null) return false;

        //Verifica se há uma senha e se ela possui entre 8 a 32 caracteres
        if(accountRegistry.getPassword().isEmpty() || accountRegistry.getPassword().length() < 8 || accountRegistry.getPassword().length() > 32) return false;

        //Verifica se há a confirmação de senha e se ambas batem
        if(accountRegistry.getConfirmPassword().isEmpty() || !accountRegistry.getConfirmPassword().equals(accountRegistry.getPassword())) return false;

        //Se tudo estiver ok, retorna verdadeiro
        return true;
    }
    //Encontra uma conta, por seu nome ou email
    private Account findAccountLogin(String identificator) {
        Methods methods = new Methods();

        Account account;

        if(methods.isValidEmail(identificator)) {
            account = accountService.findAccountByEmail(identificator);
        }
        else {
            account = accountService.findAccountByName(identificator);
        }

        return account;
    };
}

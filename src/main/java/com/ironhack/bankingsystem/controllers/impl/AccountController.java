package com.ironhack.bankingsystem.controllers.impl;

import com.ironhack.bankingsystem.controllers.dtos.*;
import com.ironhack.bankingsystem.controllers.interfaces.*;
import com.ironhack.bankingsystem.models.accounts.*;
import com.ironhack.bankingsystem.services.impl.*;
import com.ironhack.bankingsystem.services.interfaces.*;
import com.ironhack.bankingsystem.utils.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.util.*;

@RestController
public class AccountController implements AccountControllerInterface {

    @Autowired
    AccountServiceInterface accountService;

    //Only accessed by ADMIN
    @GetMapping("/admin/account/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Account getAccountById(@PathVariable("id") Long id) {
        return accountService.getAccountById(id);
    }
    @GetMapping("/admin/account/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Account getAccountByName(@PathVariable("name")@Valid AccountNameDTO name) {
        return accountService.getAccountByName(name.getName());
    }

    @PostMapping("/admin/account/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Account createAccount(@RequestBody @Valid Account account) {
        return accountService.createAccount(account);
    }

    @GetMapping("/admin/account/{id}/balance")
    @ResponseStatus(HttpStatus.OK)
    public Money getBalanceById(@PathVariable("id") Long id) {
        return accountService.getBalanceById(id);
    }

    @GetMapping("/admin/account/{name}/balance")
    @ResponseStatus(HttpStatus.OK)
    public Money getBalanceByName(@PathVariable("name")@Valid AccountNameDTO name) {
        return null;
    }

    @GetMapping("/admin/account/{id}/accounts")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> getAllAccountsFromUser(@PathVariable("id") Long userId) {
        return accountService.getAllAccountsFromUser(userId);
    }

    @PostMapping("/admin/account/{id}/balance")
    public void updateBalance(@PathVariable("id") Long accountId,@RequestBody @Valid Money money) {
        accountService.updateBalance(accountId, money);

    }

    public void getBalance() {

    }

    public Account updateDetails() {
        return null;
    }
}

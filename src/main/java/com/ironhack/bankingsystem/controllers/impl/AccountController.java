package com.ironhack.bankingsystem.controllers.impl;

import com.ironhack.bankingsystem.controllers.interfaces.*;
import com.ironhack.bankingsystem.models.accounts.*;
import com.ironhack.bankingsystem.utils.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class AccountController implements AccountControllerInterface {

    public Account getAccountById(Long id) {
        return null;
    }

    public Account getAccountByName(String name) {
        return null;
    }

    public Account createAccount(Account account) {
        return null;
    }

    public Money getBalanceById(Long id) {
        return null;
    }

    public Money getBalanceByName(String name) {
        return null;
    }

    public List<Account> getAllAccountsFromUser(Long userId) {
        return null;
    }

    public void updateBalance(Long accountId, Money money) {

    }

    public void getBalance() {

    }

    public Account updateDetails() {
        return null;
    }
}

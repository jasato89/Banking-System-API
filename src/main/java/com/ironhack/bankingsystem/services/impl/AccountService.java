package com.ironhack.bankingsystem.services.impl;

import com.ironhack.bankingsystem.models.accounts.*;
import com.ironhack.bankingsystem.repositories.*;
import com.ironhack.bankingsystem.services.interfaces.*;
import com.ironhack.bankingsystem.utils.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.*;
import org.springframework.web.server.*;

import java.util.*;

@Service
public class AccountService implements AccountServiceInterface {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountHolderRepository accountHolderRepository;

    public Account getAccountById(Long id) {
        if (accountRepository.findById(id).isPresent()) {
            return accountRepository.findById(id).get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account with id " + id + " doesn't exist in the database");
        }

    }

    public Account getAccountByName(String name) {
        if (accountRepository.findByAccountHolderName(name).isPresent() || accountRepository.findBySecondaryAccountHolderName(name).isPresent()) {
            if (accountRepository.findByAccountHolderName(name).isPresent()) {
                return accountRepository.findByAccountHolderName(name).get();
            } else {
                return accountRepository.findBySecondaryAccountHolderName(name).get();
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account with name " + name + " doesn't exist in the database");
        }
    }

    public Account createAccount(Account account) {

        if (accountRepository.findById(account.getAccountId()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Account with id " + account.getAccountId() + " already exists in the database");

        } else {
            return accountRepository.save(account);
        }

    }

    public Money getBalanceById(Long id) {

        if (accountRepository.findById(id).isPresent()) {
            return accountRepository.findById(id).get().getBalance();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account with id " + id + " doesn't exist in the database");
        }

    }

    public Money getBalanceByName(String name) {
        if (accountRepository.findByAccountHolderName(name).isPresent() || accountRepository.findBySecondaryAccountHolderName(name).isPresent()) {
            if (accountRepository.findByAccountHolderName(name).isPresent()) {
                return accountRepository.findByAccountHolderName(name).get().getBalance();
            } else {
                return accountRepository.findBySecondaryAccountHolderName(name).get().getBalance();
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account with name " + name + " doesn't exist in the database");

        }
    }

    public List<Account> getAllAccountsFromUser(Long userId) {

        if (accountHolderRepository.findById(userId).isPresent()) {
            List<Account> accounts = new ArrayList<>();
            accounts.addAll(accountHolderRepository.findById(userId).get().getPrimaryAccounts());
            accounts.addAll(accountHolderRepository.findById(userId).get().getSecondaryAccounts());
            return accounts;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id " + userId + " doesn't exist in the database");
        }
    }

    public void updateBalance(Long accountId, Money money) {

        if (accountRepository.findById(accountId).isPresent()) {

            Account account = accountRepository.findById(accountId).get();
            account.setBalance(money);
            accountRepository.save(account);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account with id " + accountId + " doesn't exist in the database");
        }


    }

    //TODO
    public void getBalance(UserDetails userDetails) {

    }
    //TODO
    public Account updateDetails(UserDetails userDetails) {
        return null;
    }
}

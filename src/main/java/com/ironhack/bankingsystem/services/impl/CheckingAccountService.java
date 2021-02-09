package com.ironhack.bankingsystem.services.impl;

import com.ironhack.bankingsystem.models.accounts.*;
import com.ironhack.bankingsystem.repositories.*;
import com.ironhack.bankingsystem.services.interfaces.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.server.*;

import java.util.*;

@Service
public class CheckingAccountService implements CheckingAccountServiceInterface {

    @Autowired
    CheckingAccountRepository checkingAccountRepository;

    public CheckingAccount createCheckingAccount(CheckingAccount checkingAccount) {

        if (checkingAccountRepository.findById(checkingAccount.getAccountId()).isPresent()) {

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Checking account with id " + checkingAccount.getAccountId() + " already exists in the database");
        } else {
            return checkingAccountRepository.save(checkingAccount);
        }
    }

    public CheckingAccount updateCheckingAccount(Long id, CheckingAccount checkingAccount) {
        if (checkingAccountRepository.findById(id).isPresent()) {
            checkingAccount.setAccountId(checkingAccountRepository.findById(id).get().getAccountId());
            return checkingAccountRepository.save(checkingAccount);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Checking account with id " + id + " doesn't in the database");

        }
    }


    public List<CheckingAccount> getAllCheckingAccounts() {
        return checkingAccountRepository.findAll();
    }
}

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
public class SavingsAccountService implements SavingsAccountServiceInterface {

    @Autowired
    SavingsAccountRepository savingsAccountRepository;

    public SavingsAccount createSavingsAccount(SavingsAccount savingsAccount) {

        if (savingsAccountRepository.findById(savingsAccount.getAccountId()).isPresent()) {

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Savings Account with id " + savingsAccount.getAccountId() + " already exists in the database");
        } else {
            return savingsAccountRepository.save(savingsAccount);
        }
    }

    public SavingsAccount updateSavingsAccount(Long id, SavingsAccount savingsAccount) {
        if (savingsAccountRepository.findById(id).isPresent()) {
            savingsAccount.setAccountId(savingsAccountRepository.findById(id).get().getAccountId());
            return savingsAccountRepository.save(savingsAccount);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Savings Account with id " + id + " doesn't in the database");

        }
    }


    public List<SavingsAccount> getAllSavingsAccounts() {
        return savingsAccountRepository.findAll();
    }

}

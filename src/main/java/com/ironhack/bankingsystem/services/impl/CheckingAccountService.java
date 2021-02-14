package com.ironhack.bankingsystem.services.impl;

import com.ironhack.bankingsystem.controllers.dtos.*;
import com.ironhack.bankingsystem.models.accounts.*;
import com.ironhack.bankingsystem.models.users.*;
import com.ironhack.bankingsystem.repositories.*;
import com.ironhack.bankingsystem.services.interfaces.*;
import com.ironhack.bankingsystem.utils.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;
import org.springframework.web.server.*;

import java.time.*;
import java.time.temporal.*;
import java.util.*;

@Service
public class CheckingAccountService implements CheckingAccountServiceInterface {

    @Autowired
    CheckingAccountRepository checkingAccountRepository;

    @Autowired
    StudentCheckingAccountRepository studentCheckingAccountRepository;

    @Autowired
    AccountHolderRepository accountHolderRepository;


    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public Account createCheckingAccount(CheckingAccountDTO checkingAccountDTO) {

        AccountHolder accountHolder;
        AccountHolder secondaryAccountHolder = null;


        if (checkingAccountDTO.getCurrency() == null) {
            checkingAccountDTO.setCurrency(Currency.getInstance("USD"));
        }
        if (!accountHolderRepository.findById(checkingAccountDTO.getAccountHolderId()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Checking account with id " + checkingAccountDTO.getAccountHolderId() + " doesn't in the database");
        } else {
            accountHolder = accountHolderRepository.findById(checkingAccountDTO.getAccountHolderId()).get();
        }

        if (checkingAccountDTO.getSecondaryAccountHolderId() != null) {
            if (!accountHolderRepository.findById(checkingAccountDTO.getSecondaryAccountHolderId()).isPresent()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Checking account with id " + checkingAccountDTO.getSecondaryAccountHolderId() + " doesn't in the database");
            } else {
                secondaryAccountHolder = accountHolderRepository.findById(checkingAccountDTO.getSecondaryAccountHolderId()).get();

            }

        }

        CheckingAccount checkingAccount = new CheckingAccount(new Money(checkingAccountDTO.getBalance(), checkingAccountDTO.getCurrency()), passwordEncoder.encode(checkingAccountDTO.getSecretKey()),  accountHolder, secondaryAccountHolder);
        if (ChronoUnit.YEARS.between(checkingAccount.getAccountHolder().getDateOfBirth(), LocalDateTime.now()) < 24) {
            StudentCheckingAccount studentCheckingAccount = new StudentCheckingAccount(checkingAccount.getBalance(), checkingAccount.getSecretKey(), checkingAccount.getAccountHolder(), checkingAccount.getSecondaryAccountHolder());
            return studentCheckingAccountRepository.save(studentCheckingAccount);

        } else {

            return checkingAccountRepository.save(checkingAccount);
        }


    }

    public CheckingAccount updateCheckingAccount(Long id, CheckingAccount checkingAccount) {
        if (checkingAccountRepository.findById(id).isPresent()) {
            checkingAccount.setAccountId(checkingAccountRepository.findById(id).get().getAccountId());
            return checkingAccountRepository.save((CheckingAccount) checkingAccount);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Checking account with id " + id + " doesn't in the database");

        }
    }


    public List<CheckingAccount> getAllCheckingAccounts() {
        return checkingAccountRepository.findAll();
    }
}

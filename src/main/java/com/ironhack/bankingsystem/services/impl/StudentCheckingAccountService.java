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

import java.util.*;

@Service
public class StudentCheckingAccountService implements StudentCheckingAccountServiceInterface {

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    AccountHolderRepository accountHolderRepository;

    @Autowired
    StudentCheckingAccountRepository studentCheckingAccountRepository;

    public StudentCheckingAccount createStudentCheckingAccount(CheckingAccountDTO checkingAccountDTO) {

        AccountHolder accountHolder;
        AccountHolder secondaryAccountHolder = null;

        if (accountHolderRepository.findById(checkingAccountDTO.getAccountHolderId()).isPresent()) {
            accountHolder = accountHolderRepository.findById(checkingAccountDTO.getAccountHolderId()).get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "AccountHolder with id " + checkingAccountDTO.getAccountHolderId() + " doesn't exist in the database");
        }

        if (checkingAccountDTO.getSecondaryAccountHolderId() != null && accountHolderRepository.findById(checkingAccountDTO.getSecondaryAccountHolderId()).isPresent()) {
            secondaryAccountHolder = accountHolderRepository.findById(checkingAccountDTO.getSecondaryAccountHolderId()).get();
        }

        StudentCheckingAccount studentCheckingAccount = new StudentCheckingAccount(
                new Money(checkingAccountDTO.getBalance(), checkingAccountDTO.getCurrency()),
                passwordEncoder.encode(checkingAccountDTO.getSecretKey()),
                accountHolder,
                secondaryAccountHolder);

        return studentCheckingAccountRepository.save(studentCheckingAccount);

    }

    public StudentCheckingAccount updateStudentCheckingAccount(Long id, StudentCheckingAccount studentCheckingAccount) {
        if (studentCheckingAccountRepository.findById(id).isPresent()) {
            studentCheckingAccount.setAccountId(studentCheckingAccountRepository.findById(id).get().getAccountId());
            return studentCheckingAccountRepository.save(studentCheckingAccount);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student Checking Account with id " + id + " doesn't exist in the database");

        }
    }


    public List<StudentCheckingAccount> getAllStudentCheckingAccounts() {
        return studentCheckingAccountRepository.findAll();
    }


}

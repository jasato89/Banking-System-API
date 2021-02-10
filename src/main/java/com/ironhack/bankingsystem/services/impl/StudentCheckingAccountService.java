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
public class StudentCheckingAccountService implements StudentCheckingAccountServiceInterface {


    @Autowired
    StudentCheckingAccountRepository studentCheckingAccountRepository;

    public StudentCheckingAccount createStudentCheckingAccount(StudentCheckingAccount studentCheckingAccount) {

        if (studentCheckingAccountRepository.findById(studentCheckingAccount.getAccountId()).isPresent()) {

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Student Checking Account with id " + studentCheckingAccount.getAccountId() + " already exists in the database");
        } else {
            return studentCheckingAccountRepository.save(studentCheckingAccount);
        }
    }

    public StudentCheckingAccount updateStudentCheckingAccount(Long id, StudentCheckingAccount studentCheckingAccount) {
        if (studentCheckingAccountRepository.findById(id).isPresent()) {
            studentCheckingAccount.setAccountId(studentCheckingAccountRepository.findById(id).get().getAccountId());
            return studentCheckingAccountRepository.save(studentCheckingAccount);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student Checking Account with id " + id + " doesn't in the database");

        }
    }


    public List<StudentCheckingAccount> getAllStudentCheckingAccounts() {
        return studentCheckingAccountRepository.findAll();
    }


}

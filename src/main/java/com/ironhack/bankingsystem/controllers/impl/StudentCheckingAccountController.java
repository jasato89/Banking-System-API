package com.ironhack.bankingsystem.controllers.impl;

import com.ironhack.bankingsystem.controllers.interfaces.*;
import com.ironhack.bankingsystem.models.accounts.*;
import com.ironhack.bankingsystem.services.interfaces.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.util.*;

@RestController
public class StudentCheckingAccountController implements StudentCheckingAccountControllerInterface {

    @Autowired
    StudentCheckingAccountServiceInterface studentAccountService;


    @GetMapping("/admin/student-checking-accounts")
    @ResponseStatus(HttpStatus.OK)
    public List<Checking> getAllStudentCheckingAccounts() {
        return studentAccountService.getAllStudentCheckingAccounts();
    }

    @PostMapping("/admin/student-checking-account/new")
    @ResponseStatus(HttpStatus.CREATED)
    public Checking createStudentCheckingAccount(@RequestBody @Valid Checking studentCheckingAccount) {
        return studentAccountService.createStudentCheckingAccount(studentCheckingAccount);
    }

    @PatchMapping("/admin/student-checking-account/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Checking updateStudentCheckingAccount(@PathVariable("id") Long id, @RequestBody @Valid Checking studentCheckingAccount) {
        return studentAccountService.updateStudentCheckingAccount(id, studentCheckingAccount);
    }
}

package com.ironhack.bankingsystem.controllers.interfaces;

import com.ironhack.bankingsystem.models.accounts.*;

import java.util.*;

public interface StudentCheckingAccountControllerInterface {
    List<StudentCheckingAccount> getAllStudentCheckingAccounts();
    StudentCheckingAccount createStudentCheckingAccount(StudentCheckingAccount studentCheckingAccount);
    StudentCheckingAccount updateStudentCheckingAccount(Long id, StudentCheckingAccount studentCheckingAccount);

}

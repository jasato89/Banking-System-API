package com.ironhack.bankingsystem.services.interfaces;

import com.ironhack.bankingsystem.models.accounts.*;

import java.util.*;

public interface StudentCheckingAccountServiceInterface {
    StudentCheckingAccount createStudentCheckingAccount(StudentCheckingAccount studentCheckingAccount);
    StudentCheckingAccount updateStudentCheckingAccount(Long id, StudentCheckingAccount studentCheckingAccount);

    List<StudentCheckingAccount> getAllStudentCheckingAccounts();
}

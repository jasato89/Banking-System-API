package com.ironhack.bankingsystem.services.interfaces;

import com.ironhack.bankingsystem.models.accounts.*;

import java.util.*;

public interface StudentCheckingAccountServiceInterface {
    Checking createStudentCheckingAccount(Checking studentCheckingAccount);
    Checking updateStudentCheckingAccount(Long id, Checking studentCheckingAccount);

    List<Checking> getAllStudentCheckingAccounts();
}

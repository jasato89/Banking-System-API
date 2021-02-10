package com.ironhack.bankingsystem.controllers.interfaces;

import com.ironhack.bankingsystem.models.accounts.*;

import java.util.*;

public interface StudentCheckingAccountControllerInterface {
    List<Checking> getAllStudentCheckingAccounts();
    Checking createStudentCheckingAccount(Checking studentCheckingAccount);
    Checking updateStudentCheckingAccount(Long id, Checking studentCheckingAccount);

}

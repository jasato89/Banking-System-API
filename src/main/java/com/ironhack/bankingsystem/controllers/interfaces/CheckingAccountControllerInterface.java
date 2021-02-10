package com.ironhack.bankingsystem.controllers.interfaces;

import com.ironhack.bankingsystem.models.accounts.*;

import java.util.*;

public interface CheckingAccountControllerInterface {
    List<CheckingAccount> getAllCheckingAccounts();
    Account createCheckingAccount(CheckingAccount checkingAccount);
    CheckingAccount updateCheckingAccount(Long id, CheckingAccount checkingAccount);
}

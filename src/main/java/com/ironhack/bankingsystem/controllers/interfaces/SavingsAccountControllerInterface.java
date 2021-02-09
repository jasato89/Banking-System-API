package com.ironhack.bankingsystem.controllers.interfaces;

import com.ironhack.bankingsystem.models.accounts.*;

import java.util.*;

public interface SavingsAccountControllerInterface {
    List<SavingsAccount> getAllSavingsAccount();
    SavingsAccount createSavingsAccount(SavingsAccount savingsAccount);
    SavingsAccount updateSavingsAccount(Long id, SavingsAccount savingsAccount);
}

package com.ironhack.bankingsystem.services.interfaces;

import com.ironhack.bankingsystem.models.accounts.*;

import java.util.*;

public interface SavingsAccountServiceInterface {
    SavingsAccount createSavingsAccount(SavingsAccount savingsAccount);
    SavingsAccount updateSavingsAccount(Long id, SavingsAccount savingsAccount);

    List<SavingsAccount> getAllSavingsAccounts();
}

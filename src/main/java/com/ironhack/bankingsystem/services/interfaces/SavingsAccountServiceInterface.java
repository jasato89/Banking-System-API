package com.ironhack.bankingsystem.services.interfaces;

import com.ironhack.bankingsystem.models.accounts.*;

public interface SavingsAccountServiceInterface {
    SavingsAccount createSavingsAccount(SavingsAccount savingsAccount);
    SavingsAccount updateSavingsAccount(SavingsAccount savingsAccount);
}

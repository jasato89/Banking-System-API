package com.ironhack.bankingsystem.controllers.interfaces;

import com.ironhack.bankingsystem.models.accounts.*;

public interface SavingsAccountControllerInterface {
    SavingsAccount createSavingsAccount(SavingsAccount savingsAccount);
    SavingsAccount updateSavingsAccount(SavingsAccount savingsAccount);
}

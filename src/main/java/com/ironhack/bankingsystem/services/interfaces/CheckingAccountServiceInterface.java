package com.ironhack.bankingsystem.services.interfaces;

import com.ironhack.bankingsystem.models.accounts.*;

public interface CheckingAccountServiceInterface {
    CheckingAccount createCheckingAccount(CheckingAccount checkingAccount);
    CheckingAccount updateCheckingAccount(CheckingAccount checkingAccount);
}

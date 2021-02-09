package com.ironhack.bankingsystem.controllers.interfaces;

import com.ironhack.bankingsystem.models.accounts.*;

public interface CheckingAccountControllerInterface {
    CheckingAccount createCheckingAccount(CheckingAccount checkingAccount);
    CheckingAccount updateCheckingAccount(CheckingAccount checkingAccount);
}

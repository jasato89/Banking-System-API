package com.ironhack.bankingsystem.controllers.interfaces;

import com.ironhack.bankingsystem.models.*;
import com.ironhack.bankingsystem.models.accounts.*;
import com.ironhack.bankingsystem.models.users.*;

import java.util.*;

public interface AccountControllerInterface {
    Account getAccountById(Long id);
    Account createAccount(Account account);
    List<Account> getAllAccountsFromUser(Long userId);
    void updateBalance(Long accountId, Money money);
}

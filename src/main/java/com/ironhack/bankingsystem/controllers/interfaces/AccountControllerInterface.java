package com.ironhack.bankingsystem.controllers.interfaces;

import com.ironhack.bankingsystem.controllers.dtos.*;
import com.ironhack.bankingsystem.models.accounts.*;
import com.ironhack.bankingsystem.utils.*;

import java.util.*;

public interface AccountControllerInterface {
    Account getAccountById(Long id);
    Account getAccountByName(AccountNameDTO name);
    Account createAccount(Account account);
    Money getBalanceById(Long id);
    Money getBalanceByName(AccountNameDTO name);
    List<Account> getAllAccountsFromUser(Long userId);
    void updateBalance(Long accountId, Money money);
    // TODO
    void getBalance();
    Account updateDetails();

}

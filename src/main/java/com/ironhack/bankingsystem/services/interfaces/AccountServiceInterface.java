package com.ironhack.bankingsystem.services.interfaces;

import com.ironhack.bankingsystem.models.accounts.*;
import com.ironhack.bankingsystem.utils.*;
import org.springframework.security.core.userdetails.*;

import java.util.*;

public interface AccountServiceInterface {
    Account getAccountById(Long id);
    Account getAccountByName(String name);
    Account createAccount(Account account);
    Money getBalanceById(Long id);
    Money getBalanceByName(String name);
    List<Account> getAllAccountsFromUser(Long userId);
    void updateBalance(Long accountId, Money money);
    // TODO
    void getBalance(UserDetails userDetails);
    Account updateDetails(UserDetails userDetails);

}

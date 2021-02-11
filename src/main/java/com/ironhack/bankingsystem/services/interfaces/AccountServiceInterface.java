package com.ironhack.bankingsystem.services.interfaces;

import com.ironhack.bankingsystem.controllers.dtos.*;
import com.ironhack.bankingsystem.models.accounts.*;
import com.ironhack.bankingsystem.utils.*;
import org.springframework.security.core.userdetails.*;

import java.util.*;

public interface AccountServiceInterface {
    AccountInfoDTO getAccountById(Long id);
    AccountInfoDTO getAccountByName(String name);
    Account createAccount(Account account);
    Money getBalanceById(Long id);
    Money getBalanceByName(String name);
    List<AccountInfoDTO> getAllAccountsFromUser(Long userId);
    void updateBalance(Long accountId, Money money);
    // TODO
    void getBalance(UserDetails userDetails);
    Account updateDetails(UserDetails userDetails);

}

package com.ironhack.bankingsystem.services.interfaces;

import com.ironhack.bankingsystem.models.users.*;

import java.util.*;

public interface AccountHolderServiceInterface {

    List<AccountHolder> getAllAccountHolders();
    AccountHolder createAccountHolder (AccountHolder accountHolder);
    AccountHolder updateDetails (AccountHolder account);



}

package com.ironhack.bankingsystem.controllers.interfaces;

import com.ironhack.bankingsystem.models.accounts.*;
import com.ironhack.bankingsystem.models.users.*;

import java.util.*;

public interface AccountHolderControllerInterface {

    List<AccountHolder> getAllAccountHolders();
    AccountHolder createAccountHolder (AccountHolder accountHolder);
    AccountHolder updateDetails (Long id, AccountHolder account);



}

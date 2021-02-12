package com.ironhack.bankingsystem.services.interfaces;

import com.ironhack.bankingsystem.controllers.dtos.*;
import com.ironhack.bankingsystem.utils.*;
import org.springframework.security.core.userdetails.*;

public interface TransactionServiceInterface {
    Money sendMoney(UserDetails userDetails, TransactionDTO transactionDTO);

}

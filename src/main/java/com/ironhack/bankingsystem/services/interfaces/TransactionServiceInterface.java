package com.ironhack.bankingsystem.services.interfaces;

import com.ironhack.bankingsystem.controllers.dtos.*;
import com.ironhack.bankingsystem.utils.*;

public interface TransactionServiceInterface {
    Money sendMoney(TransactionDTO transactionDTO);

}

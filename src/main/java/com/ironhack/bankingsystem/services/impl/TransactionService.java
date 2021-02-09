package com.ironhack.bankingsystem.services.impl;

import com.ironhack.bankingsystem.services.interfaces.*;
import com.ironhack.bankingsystem.utils.*;
import org.springframework.stereotype.*;

@Service
public class TransactionService implements TransactionServiceInterface {
    public Money sendMoney(Long senderId, Long recipientId, Money amount) {
        return null;
    }
}

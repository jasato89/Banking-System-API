package com.ironhack.bankingsystem.services.interfaces;

import com.ironhack.bankingsystem.utils.*;

public interface TransactionServiceInterface {
    Money sendMoney(Long senderId, Long recipientId, Money amount);

}

package com.ironhack.bankingsystem.controllers.interfaces;

import com.ironhack.bankingsystem.models.accounts.*;
import com.ironhack.bankingsystem.utils.*;

public interface TransactionControllerInterface {
    Money sendMoney(Long senderId, Long recipientId, Money amount);

}

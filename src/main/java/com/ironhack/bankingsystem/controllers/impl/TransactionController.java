package com.ironhack.bankingsystem.controllers.impl;

import com.ironhack.bankingsystem.controllers.interfaces.*;
import com.ironhack.bankingsystem.utils.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransactionController implements TransactionControllerInterface {
    public Money sendMoney(Long senderId, Long recipientId, Money amount) {
        return null;
    }
}

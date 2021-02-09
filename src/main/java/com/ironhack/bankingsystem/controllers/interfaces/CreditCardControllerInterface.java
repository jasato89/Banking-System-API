package com.ironhack.bankingsystem.controllers.interfaces;

import com.ironhack.bankingsystem.models.accounts.*;

public interface CreditCardControllerInterface {

    CreditCard createCreditCardAccount(CreditCard creditCard);
    CreditCard updateCreditCardAccount(CreditCard creditCard);

}

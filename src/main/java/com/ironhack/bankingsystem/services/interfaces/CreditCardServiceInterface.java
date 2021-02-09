package com.ironhack.bankingsystem.services.interfaces;

import com.ironhack.bankingsystem.models.accounts.*;

public interface CreditCardServiceInterface {

    CreditCard createCreditCardAccount(CreditCard creditCard);
    CreditCard updateCreditCardAccount(CreditCard creditCard);

}

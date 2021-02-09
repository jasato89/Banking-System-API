package com.ironhack.bankingsystem.services.interfaces;

import com.ironhack.bankingsystem.models.accounts.*;

import java.util.*;

public interface CreditCardServiceInterface {

    CreditCard createCreditCardAccount(CreditCard creditCard);
    CreditCard updateCreditCardAccount(Long id, CreditCard creditCard);

    List<CreditCard> getAllCreditCards();
}

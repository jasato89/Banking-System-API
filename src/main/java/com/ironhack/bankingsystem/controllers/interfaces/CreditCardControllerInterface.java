package com.ironhack.bankingsystem.controllers.interfaces;

import com.ironhack.bankingsystem.models.accounts.*;

import java.util.*;

public interface CreditCardControllerInterface {

    List<CreditCard> getAllCreditCards();
    CreditCard createCreditCardAccount(CreditCard creditCard);
    CreditCard updateCreditCardAccount(Long id, CreditCard creditCard);

}

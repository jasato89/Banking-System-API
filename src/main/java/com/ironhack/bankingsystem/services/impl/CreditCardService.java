package com.ironhack.bankingsystem.services.impl;

import com.ironhack.bankingsystem.controllers.dtos.*;
import com.ironhack.bankingsystem.models.accounts.*;
import com.ironhack.bankingsystem.repositories.*;
import com.ironhack.bankingsystem.services.interfaces.*;
import com.ironhack.bankingsystem.utils.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.server.*;

import java.util.*;

@Service
public class CreditCardService implements CreditCardServiceInterface {
    @Autowired
    CreditCardRepository creditCardRepository;

    public CreditCard createCreditCardAccount(CreditCardDTO creditCard) {

       return creditCardRepository.save(
               new CreditCard(new Money(creditCard.getBalance()), creditCard.getSecretKey(), creditCard.getAccountHolder(), creditCard.getSecondaryAccountHolder(), new Money(creditCard.getCreditLimit()), creditCard.getInterestRate()));
    }

    public CreditCard updateCreditCardAccount(Long id, CreditCard creditCard) {
        if (creditCardRepository.findById(id).isPresent()) {
            creditCard.setAccountId(creditCardRepository.findById(id).get().getAccountId());
            return creditCardRepository.save(creditCard);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Credit card with id " + id + " doesn't in the database");

        }
    }


    public List<CreditCard> getAllCreditCards() {
        return creditCardRepository.findAll();
    }


}

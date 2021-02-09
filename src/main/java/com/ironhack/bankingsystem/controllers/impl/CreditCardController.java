package com.ironhack.bankingsystem.controllers.impl;

import com.ironhack.bankingsystem.controllers.interfaces.*;
import com.ironhack.bankingsystem.models.accounts.*;
import com.ironhack.bankingsystem.services.interfaces.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.util.*;

@RestController
public class CreditCardController implements CreditCardControllerInterface {

    @Autowired
    CreditCardServiceInterface creditCardService;

    @GetMapping("/credit-cards")
    @ResponseStatus(HttpStatus.OK)
    public List<CreditCard> getAllCreditCards() {
        return creditCardService.getAllCreditCards();
    }


    @PostMapping("/credit-card/new")
    @ResponseStatus(HttpStatus.OK)
    public CreditCard createCreditCardAccount(@RequestBody @Valid CreditCard creditCard) {
        return creditCardService.createCreditCardAccount(creditCard);
    }

    @PostMapping("/credit-card/{id}")
    public CreditCard updateCreditCardAccount(@PathVariable("id") Long id, @RequestBody CreditCard creditCard) {
        return creditCardService.updateCreditCardAccount(id, creditCard);
    }
}

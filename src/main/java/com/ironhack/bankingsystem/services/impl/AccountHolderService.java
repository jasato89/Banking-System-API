package com.ironhack.bankingsystem.services.impl;

import com.ironhack.bankingsystem.controllers.dtos.*;
import com.ironhack.bankingsystem.models.users.*;
import com.ironhack.bankingsystem.repositories.*;
import com.ironhack.bankingsystem.services.interfaces.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.server.*;

import java.util.*;

@Service
public class AccountHolderService implements AccountHolderServiceInterface {

    @Autowired
    AccountHolderRepository accountHolderRepository;

    public List<AccountHolderInformationDTO> getAllAccountHolders() {

        List<AccountHolderInformationDTO> result = new ArrayList<>();

        for (AccountHolder accountHolder : accountHolderRepository.findAll()) {
            result.add(new AccountHolderInformationDTO(
                    accountHolder.getUsername(),
                    accountHolder.getName(),
                    accountHolder.getDateOfBirth(),
                    accountHolder.getPrimaryAddress(),
                    accountHolder.getMailingAddress()));
        }

        return result;
    }

    public AccountHolder createAccountHolder(AccountHolder accountHolder) {

        if (accountHolderRepository.findByUsername(accountHolder.getUsername()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A user with this username already exists in the database");
        } else {
            return accountHolderRepository.save(accountHolder);
        }

    }

    public AccountHolder updateDetails(Long id, AccountHolder account) {

        if (accountHolderRepository.findById(id).isPresent()) {
            account.setId(id);
            return accountHolderRepository.save(account);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A user with id " + id + "doesn't exist in the database");

        }
    }
}

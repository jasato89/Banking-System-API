package com.ironhack.bankingsystem.services.impl;

import com.ironhack.bankingsystem.models.users.*;
import com.ironhack.bankingsystem.repositories.*;
import com.ironhack.bankingsystem.services.interfaces.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.server.*;

import java.util.*;

@Service
public class ThirdPartyService implements ThirdPartyServiceInterface {

    @Autowired
    ThirdPartyRepository thirdPartyRepository;

    public ThirdParty createThirdParty(ThirdParty thirdParty) {
        if (thirdPartyRepository.findById(thirdParty.getId()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Third Party with id " + thirdParty.getId() + " already exists in the database");
        } else {
            return thirdPartyRepository.save(thirdParty);
        }
    }

    public ThirdParty updateThirdParty(Long id, ThirdParty thirdParty) {

        if (thirdPartyRepository.findById(id).isPresent()) {
            thirdParty.setId(id);
            return thirdPartyRepository.save(thirdParty);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Third Party with id " + id + " doesn't exist in the database");
        }
    }

    @Override
    public List<ThirdParty> getAllThirdPartyAccounts() {
        return thirdPartyRepository.findAll();
    }
}

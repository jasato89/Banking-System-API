package com.ironhack.bankingsystem.services.interfaces;

import com.ironhack.bankingsystem.models.users.*;

import java.util.*;

public interface ThirdPartyServiceInterface {

    ThirdParty createThirdParty(ThirdParty thirdParty);
    ThirdParty updateThirdParty(Long id, @Valid ThirdParty thirdParty);
    List<ThirdParty> getAllThirdPartyAccounts();
}

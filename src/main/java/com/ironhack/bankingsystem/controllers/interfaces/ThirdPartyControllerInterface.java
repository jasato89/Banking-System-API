package com.ironhack.bankingsystem.controllers.interfaces;

import com.ironhack.bankingsystem.models.users.*;

import java.util.*;

public interface ThirdPartyControllerInterface {

    List<ThirdParty> getAllThirdPartyAccounts();
    ThirdParty createThirdParty(ThirdParty thirdParty);
    ThirdParty updateThirdParty(Long id, ThirdParty thirdParty);


}

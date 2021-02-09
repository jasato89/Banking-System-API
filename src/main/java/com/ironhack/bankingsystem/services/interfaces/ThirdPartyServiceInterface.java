package com.ironhack.bankingsystem.services.interfaces;

import com.ironhack.bankingsystem.models.users.*;

public interface ThirdPartyServiceInterface {

    ThirdParty createThirdParty(ThirdParty thirdParty);
    ThirdParty updateThirdParty(Long id);


}

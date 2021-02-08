package com.ironhack.bankingsystem.controllers.interfaces;

import com.ironhack.bankingsystem.models.users.*;

public interface ThirdPartyControllerInterface {

    ThirdParty createThirdParty(ThirdParty thirdParty);
    ThirdParty updateThirdParty(Long id);


}

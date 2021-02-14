package com.ironhack.bankingsystem.controllers.impl;

import com.ironhack.bankingsystem.controllers.dtos.*;
import com.ironhack.bankingsystem.controllers.interfaces.*;
import com.ironhack.bankingsystem.models.users.*;
import com.ironhack.bankingsystem.services.interfaces.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.util.*;

@RestController
public class ThirdPartyController implements ThirdPartyControllerInterface {

    @Autowired
    ThirdPartyServiceInterface thirdPartyService;

    @GetMapping("/admin/third-party-accounts")
    @ResponseStatus(HttpStatus.OK)
    public List<ThirdParty> getAllThirdPartyAccounts() {
        return thirdPartyService.getAllThirdPartyAccounts();
    }

    @PostMapping("/admin/third-party-account/new")
    @ResponseStatus(HttpStatus.CREATED)
    public ThirdParty createThirdParty(@RequestBody @Valid ThirdParty thirdParty) {
        return thirdPartyService.createThirdParty(thirdParty);
    }

    @PostMapping("/third-party/send-money")
    public void sendMoney(ThirdPartyTransactionDTO thirdPartyTransactionDTO) {

    }

    @PostMapping("/third-party/receive-money")
    public void receiveMoney(ThirdPartyTransactionDTO thirdPartyTransactionDTO) {

    }

}

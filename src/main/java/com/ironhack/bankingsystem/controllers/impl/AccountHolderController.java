package com.ironhack.bankingsystem.controllers.impl;

import com.ironhack.bankingsystem.controllers.interfaces.*;
import com.ironhack.bankingsystem.models.users.*;
import com.ironhack.bankingsystem.services.interfaces.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.util.*;

@RestController
public class AccountHolderController implements AccountHolderControllerInterface {

    @Autowired
    AccountHolderServiceInterface accountHolderService;

    @GetMapping("/admin/accountHolders")
    public List<AccountHolder> getAllAccountHolders() {
        return accountHolderService.getAllAccountHolders();
    }

    @PostMapping("admin/account-holder/new/")
    public AccountHolder createAccountHolder(AccountHolder accountHolder) {
        return accountHolderService.createAccountHolder(accountHolder);
    }

    @PostMapping("admin/account-holder/{id}/")
    public AccountHolder updateDetails(@PathVariable("id") Long id, @RequestBody @Valid AccountHolder account) {
        return accountHolderService.updateDetails(id, account);
    }
}

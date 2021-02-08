package com.ironhack.bankingsystem.models.account;

import com.ironhack.bankingsystem.enums.*;
import com.ironhack.bankingsystem.models.*;
import com.ironhack.bankingsystem.models.users.*;

import javax.persistence.*;
import javax.validation.constraints.*;

public class StudentCheckingAccount extends Account{
    @Enumerated
    private Status status;

    public StudentCheckingAccount(Long id, Money balance, String secretKey, @NotNull AccountHolder accountHolder, Status status) {
        super(id, balance, secretKey, accountHolder);
        this.status = status;
    }

    public StudentCheckingAccount(Long id, Money balance, String secretKey, @NotNull AccountHolder accountHolder, AccountHolder secondaryAccountHolder, Status status) {
        super(id, balance, secretKey, accountHolder, secondaryAccountHolder);
        this.status = status;
    }

    public StudentCheckingAccount(Long id, Money balance, String secretKey, boolean isPenalized, AccountHolder accountHolder, AccountHolder secondaryAccountHolder, Status status) {
        super(id, balance, secretKey, isPenalized, accountHolder, secondaryAccountHolder);
        this.status = status;
    }

    public StudentCheckingAccount(Long id, Money balance, String secretKey, boolean isPenalized, AccountHolder accountHolder, Status status) {
        super(id, balance, secretKey, isPenalized, accountHolder);
        this.status = status;
    }
}

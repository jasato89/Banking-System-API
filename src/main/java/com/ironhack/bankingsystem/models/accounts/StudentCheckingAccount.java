package com.ironhack.bankingsystem.models.accounts;

import com.ironhack.bankingsystem.enums.*;
import com.ironhack.bankingsystem.models.users.*;
import com.ironhack.bankingsystem.utils.*;

import javax.persistence.*;
import javax.validation.*;
import javax.validation.constraints.*;

@Entity
@PrimaryKeyJoinColumn(name = "accountId")
public class StudentCheckingAccount extends Checking{
    @Enumerated
    private Status status;

    public StudentCheckingAccount(){status = Status.ACTIVE;}

    public StudentCheckingAccount(Money balance, String secretKey, boolean isPenalized, @NotNull @Valid AccountHolder accountHolder, @Valid AccountHolder secondaryAccountHolder) {
        super(balance, secretKey, isPenalized, accountHolder, secondaryAccountHolder);
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}

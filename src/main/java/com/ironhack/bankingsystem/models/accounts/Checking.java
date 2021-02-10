package com.ironhack.bankingsystem.models.accounts;

import com.ironhack.bankingsystem.enums.*;
import com.ironhack.bankingsystem.models.users.*;
import com.ironhack.bankingsystem.utils.*;

import javax.persistence.*;
import javax.validation.*;
import javax.validation.constraints.*;


@Table(name = "student_checking_account")
public class Checking extends Account {
    @Enumerated
    private Status status;

    public Checking(){}

    public Checking(Money balance, String secretKey, boolean isPenalized, @NotNull @Valid AccountHolder accountHolder, @Valid AccountHolder secondaryAccountHolder) {
        super(balance, secretKey, isPenalized, accountHolder, secondaryAccountHolder);
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}

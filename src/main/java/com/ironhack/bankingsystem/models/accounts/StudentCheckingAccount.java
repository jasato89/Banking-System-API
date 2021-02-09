package com.ironhack.bankingsystem.models.accounts;

import com.ironhack.bankingsystem.enums.*;
import com.ironhack.bankingsystem.models.users.*;
import com.ironhack.bankingsystem.utils.*;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@PrimaryKeyJoinColumn(name = "accountId")
@Table(name = "student_checking_account")
public class StudentCheckingAccount extends Account{
    @Enumerated
    private Status status;

    public StudentCheckingAccount(){}
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

    public StudentCheckingAccount(Long id, Money balance, String secretKey, @NotNull AccountHolder accountHolder) {
        super(id, balance, secretKey, accountHolder);
        this.status = Status.ACTIVE;
    }

    public StudentCheckingAccount(Long id, Money balance, String secretKey, @NotNull AccountHolder accountHolder, AccountHolder secondaryAccountHolder) {
        super(id, balance, secretKey, accountHolder, secondaryAccountHolder);
        this.status = Status.ACTIVE;
    }

    public StudentCheckingAccount(Long id, Money balance, String secretKey, boolean isPenalized, AccountHolder accountHolder, AccountHolder secondaryAccountHolder) {
        super(id, balance, secretKey, isPenalized, accountHolder, secondaryAccountHolder);
        this.status = Status.ACTIVE;
    }

    public StudentCheckingAccount(Long id, Money balance, String secretKey, boolean isPenalized, AccountHolder accountHolder) {
        super(id, balance, secretKey, isPenalized, accountHolder);
        this.status = Status.ACTIVE;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
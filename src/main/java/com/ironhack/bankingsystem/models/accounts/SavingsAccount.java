package com.ironhack.bankingsystem.models.accounts;

import com.ironhack.bankingsystem.enums.*;
import com.ironhack.bankingsystem.models.*;
import com.ironhack.bankingsystem.models.users.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.*;

@Entity
@PrimaryKeyJoinColumn(name = "accountId")
@Table(name = "savings_account")
public class SavingsAccount extends Account{

    private double interestRate;
    @ColumnDefault("Status.ACTIVE")
    private Status status;

    public SavingsAccount() {}

    public SavingsAccount(Long id, Money balance, String secretKey, @NotNull AccountHolder accountHolder, double interestRate, Status status) {
        super(id, balance, secretKey, accountHolder);
        this.interestRate = interestRate;
        this.status = status;
    }

    public SavingsAccount(Long id, Money balance, String secretKey, @NotNull AccountHolder accountHolder, AccountHolder secondaryAccountHolder, double interestRate, Status status) {
        super(id, balance, secretKey, accountHolder, secondaryAccountHolder);
        this.interestRate = interestRate;
        this.status = status;
    }

    public SavingsAccount(Long id, Money balance, String secretKey, boolean isPenalized, AccountHolder accountHolder, AccountHolder secondaryAccountHolder, double interestRate, Status status) {
        super(id, balance, secretKey, isPenalized, accountHolder, secondaryAccountHolder);
        this.interestRate = interestRate;
        this.status = status;
    }

    public SavingsAccount(Long id, Money balance, String secretKey, boolean isPenalized, AccountHolder accountHolder, double interestRate, Status status) {
        super(id, balance, secretKey, isPenalized, accountHolder);
        this.interestRate = interestRate;
        this.status = status;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}

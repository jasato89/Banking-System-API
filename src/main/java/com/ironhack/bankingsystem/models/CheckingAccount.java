package com.ironhack.bankingsystem.models;

import com.ironhack.bankingsystem.enums.*;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@PrimaryKeyJoinColumn(name = "accountId")
public class CheckingAccount extends Account {
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "currency", column = @Column(name = "minimum_balance_currency")),
            @AttributeOverride(name = "amount", column = @Column(name = "minimum_balance_amount"))
    })
    private Money minimumBalance;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "currency", column = @Column(name = "monthly_maintenance_fee_currency")),
            @AttributeOverride(name = "amount", column = @Column(name = "monthly_maintenance_fee_amount"))
    })
    private Money monthlyMaintenanceFee;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    public CheckingAccount() {

    }

    public CheckingAccount(Long id, Money balance, String secretKey, @NotNull AccountHolder accountHolder, Money minimumBalance, Money monthlyMaintenanceFee) {
        super(id, balance, secretKey, accountHolder);
        this.minimumBalance = minimumBalance;
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
        this.status = Status.ACTIVE;
    }

    public CheckingAccount(Long id, Money balance, String secretKey, @NotNull AccountHolder accountHolder, AccountHolder secondaryAccountHolder, Money minimumBalance, Money monthlyMaintenanceFee) {
        super(id, balance, secretKey, accountHolder, secondaryAccountHolder);
        this.minimumBalance = minimumBalance;
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
        this.status = Status.ACTIVE;
    }

    public CheckingAccount(Long id, Money balance, String secretKey, boolean isPenalized, AccountHolder accountHolder, AccountHolder secondaryAccountHolder, Money minimumBalance, Money monthlyMaintenanceFee) {
        super(id, balance, secretKey, isPenalized, accountHolder, secondaryAccountHolder);
        this.minimumBalance = minimumBalance;
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
        this.status = Status.ACTIVE;
    }

    public CheckingAccount(Long id, Money balance, String secretKey, boolean isPenalized, AccountHolder accountHolder, Money minimumBalance, Money monthlyMaintenanceFee) {
        super(id, balance, secretKey, isPenalized, accountHolder);
        this.minimumBalance = minimumBalance;
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
        this.status = Status.ACTIVE;
    }

    public CheckingAccount(Long id, Money balance, String secretKey, @NotNull AccountHolder accountHolder, Money minimumBalance, Money monthlyMaintenanceFee, Status status) {
        super(id, balance, secretKey, accountHolder);
        this.minimumBalance = minimumBalance;
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
        this.status = status;
    }

    public CheckingAccount(Long id, Money balance, String secretKey, @NotNull AccountHolder accountHolder, AccountHolder secondaryAccountHolder, Money minimumBalance, Money monthlyMaintenanceFee, Status status) {
        super(id, balance, secretKey, accountHolder, secondaryAccountHolder);
        this.minimumBalance = minimumBalance;
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
        this.status = status;
    }

    public CheckingAccount(Long id, Money balance, String secretKey, boolean isPenalized, AccountHolder accountHolder, AccountHolder secondaryAccountHolder, Money minimumBalance, Money monthlyMaintenanceFee, Status status) {
        super(id, balance, secretKey, isPenalized, accountHolder, secondaryAccountHolder);
        this.minimumBalance = minimumBalance;
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
        this.status = status;
    }

    public CheckingAccount(Long id, Money balance, String secretKey, boolean isPenalized, AccountHolder accountHolder, Money minimumBalance, Money monthlyMaintenanceFee, Status status) {
        super(id, balance, secretKey, isPenalized, accountHolder);
        this.minimumBalance = minimumBalance;
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
        this.status = status;
    }

    public Money getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(Money minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    public Money getMonthlyMaintenanceFee() {
        return monthlyMaintenanceFee;
    }

    public void setMonthlyMaintenanceFee(Money monthlyMaintenanceFee) {
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
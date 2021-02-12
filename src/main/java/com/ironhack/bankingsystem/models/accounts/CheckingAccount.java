package com.ironhack.bankingsystem.models.accounts;

import com.ironhack.bankingsystem.enums.*;
import com.ironhack.bankingsystem.models.users.*;
import com.ironhack.bankingsystem.utils.*;

import javax.persistence.*;
import javax.validation.*;
import javax.validation.constraints.*;
import java.math.*;
import java.time.*;
import java.util.*;

@Entity
@PrimaryKeyJoinColumn(name = "accountId")
public class CheckingAccount extends Account {
    LocalDateTime maintenanceFeeLastTimeApplied;
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


    public CheckingAccount(@DecimalMin(value = "250", message = "Interest rate must be above 0 or 0") Money balance, String secretKey, @NotNull @Valid AccountHolder accountHolder, @Valid AccountHolder secondaryAccountHolder, Money minimumBalance, Money monthlyMaintenanceFee) {
        super(balance, secretKey, accountHolder, secondaryAccountHolder);
        setMinimumBalance(minimumBalance);
        setMonthlyMaintenanceFee(monthlyMaintenanceFee);
        status = Status.ACTIVE;
        maintenanceFeeLastTimeApplied = LocalDateTime.now();
    }


    public Money getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(Money minimumBalance) {
        this.minimumBalance = minimumBalance == null ? new Money(Constants.CHECKING_ACC_MIN_BALANCE) : minimumBalance;
    }

    public Money getMonthlyMaintenanceFee() {
        return monthlyMaintenanceFee;
    }

    public void setMonthlyMaintenanceFee(Money monthlyMaintenanceFee) {
        this.monthlyMaintenanceFee = monthlyMaintenanceFee == null ? new Money(Constants.CHECKING_ACC_DEFFAULT_MONTHLY_FEE) : monthlyMaintenanceFee;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getMaintenanceFeeLastTimeApplied() {
        return maintenanceFeeLastTimeApplied;
    }

    public void setMaintenanceFeeLastTimeApplied(LocalDateTime maintenanceFeeLastTimeApplied) {
        this.maintenanceFeeLastTimeApplied = maintenanceFeeLastTimeApplied;
    }
}

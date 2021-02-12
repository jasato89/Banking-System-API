package com.ironhack.bankingsystem.models.accounts;

import com.ironhack.bankingsystem.enums.*;
import com.ironhack.bankingsystem.models.users.*;
import com.ironhack.bankingsystem.utils.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.*;
import javax.validation.constraints.*;
import java.math.*;

@Entity
@PrimaryKeyJoinColumn(name = "accountId")
@Table(name = "savings_account")
public class SavingsAccount extends Account {
    @DecimalMax(value = "0.5", message = "Interest rate must be below 0.5")
    @DecimalMin(value = "0", message = "Interest rate must be above 0 or 0")
    private BigDecimal interestRate;
    @DecimalMax(value = "1000", message = "Minimum balance must be below 0.5")
    @DecimalMin(value = "100", message = "Minimum balance must be above 0 or 0")
    private BigDecimal minimumBalance;
    private Status status;

    public SavingsAccount() {status = Status.ACTIVE;}

    public SavingsAccount(Money balance, String secretKey,  @NotNull @Valid AccountHolder accountHolder, @Valid AccountHolder secondaryAccountHolder, @DecimalMax(value = "0.5", message = "Interest rate must be below 0.5") @DecimalMin(value = "0", message = "Interest rate must be above 0 or 0") BigDecimal interestRate, BigDecimal minimumBalance) {
        super(balance, secretKey, accountHolder, secondaryAccountHolder);
        setInterestRate(interestRate);
        setMinimumBalance(minimumBalance);

    }

    public BigDecimal getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(BigDecimal minimumBalance) {
        this.minimumBalance = minimumBalance == null ? Constants.SAVINGS_ACC_DEFAULT_MIN_BALANCE : minimumBalance;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {

        this.interestRate = interestRate == null ? Constants.SAVINGS_ACC_DEFAULT_INTEREST_RATE : interestRate;;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}

package com.ironhack.bankingsystem.models.accounts;

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
@Table(name = "credit_card")
public class CreditCard  extends Account{
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "currency", column = @Column(name = "credit_limit_currency")),
            @AttributeOverride(name = "amount", column = @Column(name = "credit_limit_amount"))
    })
    @DecimalMax(value = "10000", message = "Max credit limit must be below 10000")
    @DecimalMin(value = "100", message = "Min credit limit must be above 100")
    private Money creditLimit;
    @DecimalMax(value = "0.2", message = "Max interest rate must be below 0.2")
    @DecimalMin(value = "0.1", message = "Min interest rate must be above 0.1")
    private BigDecimal interestRate;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "currency", column = @Column(name = "penalty_fee_currency")),
            @AttributeOverride(name = "amount", column = @Column(name = "penalty_fee_amount"))
    })
    private Money penaltyFee = Constants.PENALTY_FEE;
    private LocalDateTime lastInterestApplied;


    public CreditCard() {
    }

    public CreditCard(Money balance, String secretKey, boolean isPenalized, @NotNull @Valid AccountHolder accountHolder, @Valid AccountHolder secondaryAccountHolder, @DecimalMax(value = "10000", message = "Max credit limit must be below 10000") @DecimalMin(value = "100", message = "Min credit limit must be above 100") Money creditLimit, @DecimalMax(value = "0.2", message = "Max interest rate must be below 0.2") @DecimalMin(value = "0.1", message = "Min interest rate must be above 0.1") BigDecimal interestRate) {
        super(balance, secretKey, isPenalized, accountHolder, secondaryAccountHolder);
        setCreditLimit(creditLimit);
        setInterestRate(interestRate);
        lastInterestApplied = LocalDateTime.now();


    }

    public LocalDateTime getLastInterestApplied() {
        return lastInterestApplied;
    }

    public void setLastInterestApplied(LocalDateTime lastInterestApplied) {
        this.lastInterestApplied = lastInterestApplied;
    }

    public Money getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(Money creditLimit) {
        this.creditLimit = creditLimit;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public Money getPenaltyFee() {
        return penaltyFee;
    }

    public void setPenaltyFee(Money penaltyFee) {
        this.penaltyFee = penaltyFee;
    }
}

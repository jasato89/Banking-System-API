package com.ironhack.bankingsystem.models.accounts;

import com.ironhack.bankingsystem.models.users.*;
import com.ironhack.bankingsystem.utils.*;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@PrimaryKeyJoinColumn(name = "accountId")
@Table(name = "credit_card")
public class CreditCard  extends Account{
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "currency", column = @Column(name = "credit_limit_currency")),
            @AttributeOverride(name = "amount", column = @Column(name = "credit_limit_amount"))
    })
    private Money creditLimit;
    private double interestRate;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "currency", column = @Column(name = "penalty_fee_currency")),
            @AttributeOverride(name = "amount", column = @Column(name = "penalty_fee_amount"))
    })
    private Money penaltyFee;

    public CreditCard() {
    }

    public CreditCard(Long id, Money balance, String secretKey, @NotNull AccountHolder accountHolder, Money creditLimit, double interestRate, Money penaltyFee) {
        super(id, balance, secretKey, accountHolder);
        this.creditLimit = creditLimit;
        this.interestRate = interestRate;
        this.penaltyFee = penaltyFee;
    }

    public CreditCard(Long id, Money balance, String secretKey, @NotNull AccountHolder accountHolder, AccountHolder secondaryAccountHolder, Money creditLimit, double interestRate, Money penaltyFee) {
        super(id, balance, secretKey, accountHolder, secondaryAccountHolder);
        this.creditLimit = creditLimit;
        this.interestRate = interestRate;
        this.penaltyFee = penaltyFee;
    }

    public CreditCard(Long id, Money balance, String secretKey, boolean isPenalized, AccountHolder accountHolder, AccountHolder secondaryAccountHolder, Money creditLimit, double interestRate, Money penaltyFee) {
        super(id, balance, secretKey, isPenalized, accountHolder, secondaryAccountHolder);
        this.creditLimit = creditLimit;
        this.interestRate = interestRate;
        this.penaltyFee = penaltyFee;
    }

    public CreditCard(Long id, Money balance, String secretKey, boolean isPenalized, AccountHolder accountHolder, Money creditLimit, double interestRate, Money penaltyFee) {
        super(id, balance, secretKey, isPenalized, accountHolder);
        this.creditLimit = creditLimit;
        this.interestRate = interestRate;
        this.penaltyFee = penaltyFee;
    }

    public Money getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(Money creditLimit) {
        this.creditLimit = creditLimit;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public Money getPenaltyFee() {
        return penaltyFee;
    }

    public void setPenaltyFee(Money penaltyFee) {
        this.penaltyFee = penaltyFee;
    }
}

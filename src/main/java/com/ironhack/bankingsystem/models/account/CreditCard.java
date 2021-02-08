package com.ironhack.bankingsystem.models.account;

import com.ironhack.bankingsystem.models.*;
import com.ironhack.bankingsystem.models.users.*;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@PrimaryKeyJoinColumn(name = "id")
@Table(name = "credit_card")
public class CreditCard  extends Account{
    private Money creditLimit;
    private Money interestRate;
    private Money penaltyFee;

    public CreditCard() {
    }

    public CreditCard(Long id, Money balance, String secretKey, @NotNull AccountHolder accountHolder, Money creditLimit, Money interestRate, Money penaltyFee) {
        super(id, balance, secretKey, accountHolder);
        this.creditLimit = creditLimit;
        this.interestRate = interestRate;
        this.penaltyFee = penaltyFee;
    }

    public CreditCard(Long id, Money balance, String secretKey, @NotNull AccountHolder accountHolder, AccountHolder secondaryAccountHolder, Money creditLimit, Money interestRate, Money penaltyFee) {
        super(id, balance, secretKey, accountHolder, secondaryAccountHolder);
        this.creditLimit = creditLimit;
        this.interestRate = interestRate;
        this.penaltyFee = penaltyFee;
    }

    public CreditCard(Long id, Money balance, String secretKey, boolean isPenalized, AccountHolder accountHolder, AccountHolder secondaryAccountHolder, Money creditLimit, Money interestRate, Money penaltyFee) {
        super(id, balance, secretKey, isPenalized, accountHolder, secondaryAccountHolder);
        this.creditLimit = creditLimit;
        this.interestRate = interestRate;
        this.penaltyFee = penaltyFee;
    }

    public CreditCard(Long id, Money balance, String secretKey, boolean isPenalized, AccountHolder accountHolder, Money creditLimit, Money interestRate, Money penaltyFee) {
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

    public Money getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Money interestRate) {
        this.interestRate = interestRate;
    }

    public Money getPenaltyFee() {
        return penaltyFee;
    }

    public void setPenaltyFee(Money penaltyFee) {
        this.penaltyFee = penaltyFee;
    }
}

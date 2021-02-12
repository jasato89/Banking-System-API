package com.ironhack.bankingsystem.controllers.dtos;

import com.ironhack.bankingsystem.models.users.*;
import net.bytebuddy.implementation.bind.annotation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.context.properties.bind.*;

import javax.validation.*;
import javax.validation.constraints.*;
import java.math.*;
import java.util.*;

public class CreditCardDTO {
    @Valid
    private Currency currency;
    @DecimalMax(value = "10000", message = "Max credit limit must be below 10000")
    @DecimalMin(value = "100", message = "Min credit limit must be above 100")
    private BigDecimal creditLimit;
    @DecimalMin(value = "0.1", message = "Min interest rate must be above 0.1")
    private BigDecimal interestRate = new BigDecimal("0.1");
    @NotNull
    @DecimalMin(value = "0", message = "Minimum Balance must be zero or above zero")
    private BigDecimal balance;
    @NotNull
    private String secretKey;
    @NotNull
    @Valid
    private AccountHolder accountHolder;
    private AccountHolder secondaryAccountHolder;

    public CreditCardDTO(@Valid Currency currency, @DecimalMax(value = "10000", message = "Max credit limit must be below 10000") @DecimalMin(value = "100", message = "Min credit limit must be above 100") BigDecimal creditLimit, @DecimalMax(value = "0.2", message = "Max interest rate must be below 0.2") @DecimalMin(value = "0.1", message = "Min interest rate must be above 0.1") BigDecimal interestRate, @NotNull @DecimalMin(value = "0", message = "Minimum Balance must be zero or above zero") BigDecimal balance, @NotNull String secretKey, @NotNull @Valid AccountHolder accountHolder, AccountHolder secondaryAccountHolder) {
        this.currency = currency;
        this.creditLimit = creditLimit == null?  new BigDecimal("100") : creditLimit;;
        this.interestRate = interestRate == null?  new BigDecimal("0.1") : interestRate;
        this.balance = balance;
        this.secretKey = secretKey;
        this.accountHolder = accountHolder;
        this.secondaryAccountHolder = secondaryAccountHolder;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public AccountHolder getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(AccountHolder accountHolder) {
        this.accountHolder = accountHolder;
    }
}



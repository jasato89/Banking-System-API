package com.ironhack.bankingsystem.models.accounts;

import com.ironhack.bankingsystem.models.*;
import com.ironhack.bankingsystem.models.users.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.*;
import javax.validation.constraints.*;
import java.time.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;
    private Money balance;
    private String secretKey;
    @ColumnDefault(value = "false")
    private boolean isPenalized;
    @ManyToOne
    @NotNull
    @Valid
    @JoinColumn(name = "account_holder")
    private AccountHolder accountHolder;
    @ManyToOne
    @Valid
    @JoinColumn(name = "secondary_account_holder")
    private AccountHolder secondaryAccountHolder;
    private LocalDateTime creationDate;

    public Account(Long accountId, Money balance, String secretKey, @NotNull AccountHolder accountHolder) {
        this.accountId = accountId;
        this.balance = balance;
        this.secretKey = secretKey;
        this.accountHolder = accountHolder;
        creationDate = LocalDateTime.now();

    }

    public Account(Long accountId, Money balance, String secretKey, @NotNull AccountHolder accountHolder, AccountHolder secondaryAccountHolder) {
        this.accountId = accountId;
        this.balance = balance;
        this.secretKey = secretKey;
        this.accountHolder = accountHolder;
        this.secondaryAccountHolder = secondaryAccountHolder;
        creationDate = LocalDateTime.now();

    }

    public Account(Long accountId, Money balance, String secretKey, boolean isPenalized, AccountHolder accountHolder, AccountHolder secondaryAccountHolder) {
        this.accountId = accountId;
        this.balance = balance;
        this.secretKey = secretKey;
        this.isPenalized = isPenalized;
        this.accountHolder = accountHolder;
        this.secondaryAccountHolder = secondaryAccountHolder;
        creationDate = LocalDateTime.now();
    }

    public Account(Long accountId, Money balance, String secretKey, boolean isPenalized, AccountHolder accountHolder) {
        this.accountId = accountId;
        this.balance = balance;
        this.secretKey = secretKey;
        this.isPenalized = isPenalized;
        this.accountHolder = accountHolder;
        creationDate = LocalDateTime.now();
    }

    public Account(){creationDate = LocalDateTime.now();}


    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long id) {
        this.accountId = id;
    }

    public Money getBalance() {
        return balance;
    }

    public void setBalance(Money balance) {
        this.balance = balance;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public boolean isPenalized() {
        return isPenalized;
    }

    public void setPenalized(boolean penalized) {
        isPenalized = penalized;
    }

    public AccountHolder getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(AccountHolder accountHolder) {
        this.accountHolder = accountHolder;
    }

    public AccountHolder getSecondaryAccountHolder() {
        return secondaryAccountHolder;
    }

    public void setSecondaryAccountHolder(AccountHolder secondaryAccountHolder) {
        this.secondaryAccountHolder = secondaryAccountHolder;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
}

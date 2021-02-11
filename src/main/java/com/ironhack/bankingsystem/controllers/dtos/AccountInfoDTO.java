package com.ironhack.bankingsystem.controllers.dtos;

import com.ironhack.bankingsystem.utils.*;

import java.time.*;

public class AccountInfoDTO {

    private Long accountId;
    private Money balance;
    private String secretKey;
    private boolean isPenalized;
    private AccountHolderInformationDTO accountHolder;
    private AccountHolderInformationDTO secondaryAccountHolder;
    private LocalDateTime creationDate;

    public AccountInfoDTO(Long accountId, Money balance, String secretKey, boolean isPenalized, AccountHolderInformationDTO accountHolder, AccountHolderInformationDTO secondaryAccountHolder, LocalDateTime creationDate) {
        this.accountId = accountId;
        this.balance = balance;
        this.secretKey = secretKey;
        this.isPenalized = isPenalized;
        this.accountHolder = accountHolder;
        this.secondaryAccountHolder = secondaryAccountHolder;
        this.creationDate = creationDate;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
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

    public AccountHolderInformationDTO getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(AccountHolderInformationDTO accountHolder) {
        this.accountHolder = accountHolder;
    }

    public AccountHolderInformationDTO getSecondaryAccountHolder() {
        return secondaryAccountHolder;
    }

    public void setSecondaryAccountHolder(AccountHolderInformationDTO secondaryAccountHolder) {
        this.secondaryAccountHolder = secondaryAccountHolder;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
}

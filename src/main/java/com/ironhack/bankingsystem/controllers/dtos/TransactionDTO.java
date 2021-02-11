package com.ironhack.bankingsystem.controllers.dtos;

import com.ironhack.bankingsystem.utils.*;

public class TransactionDTO {

    private Long senderId;
    private Long recipientId;
    private Money amount;

    public TransactionDTO(Long senderId, Long recipientId, Money amount) {
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.amount = amount;

    }

    public TransactionDTO() {
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(Long recipientId) {
        this.recipientId = recipientId;
    }

    public Money getAmount() {
        return amount;
    }

    public void setAmount(Money amount) {
        this.amount = amount;
    }
}

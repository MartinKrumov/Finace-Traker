package com.example.model.pojo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {
    private long transactionId;
    private TransactionType type;
    private BigDecimal amount;
    private LocalDateTime date;
    private String description;
    private long categoryId;
    private long walletId;

    public Transaction(long transactionId, TransactionType type, BigDecimal amount, LocalDateTime date, String description, long categoryId) {
        this.transactionId = transactionId;
        this.type = type;
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.categoryId = categoryId;
//        this.walletId = walletId;
    }

    public Transaction(){}

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public TransactionType getType() {
        return type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public void setWalletId(long walletId) {
        this.walletId = walletId;
    }

    public long getWalletId() {
        return walletId;
    }
}
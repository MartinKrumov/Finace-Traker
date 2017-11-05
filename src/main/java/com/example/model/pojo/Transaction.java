package com.example.model.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Transaction implements Comparable< Transaction > {
    private long transactionId;
    private TransactionType type;
    private BigDecimal amount;
    private Date date;
    private String description;
    private long categoryId;
    private long walletId;

    public Transaction() {
    }

    public Transaction(long transactionId, TransactionType type, BigDecimal amount, Date date, String description, long categoryId, long walletId) {
        this.transactionId = transactionId;
        this.type = type;
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.categoryId = categoryId;
        this.walletId = walletId;
    }

    public Transaction(TransactionType type, BigDecimal amount, String description, long categoryId, long walletId) {
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.categoryId = categoryId;
        this.walletId = walletId;
    }

    public Transaction(long transactionId, TransactionType type, BigDecimal amount, Date date, long categoryId, long walletId) {
        this.transactionId = transactionId;
        this.type = type;
        this.amount = amount;
        this.date = date;
        this.categoryId = categoryId;
        this.walletId = walletId;
    }

    public Transaction(TransactionType type, BigDecimal amount, Date date, String description, long categoryId, long walletId) {
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.date = date;
        this.categoryId = categoryId;
        this.walletId = walletId;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setDate(Date date) {
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

    public Date getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public long getWalletId() {
        return walletId;
    }

    @Override
    public int compareTo(Transaction o) {
        return ( int ) (this.getTransactionId() - o.getTransactionId());
    }
}
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

	public Transaction(TransactionType type, BigDecimal amount, LocalDateTime date, String description, long categoryId , long walletId) {
        this.type = type;
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.categoryId = categoryId;
        this.walletId = walletId;
    }

	public Transaction(long transactionId, TransactionType type, BigDecimal amount, LocalDateTime date, String description, long categoryId, long walletId) {
		this(type, amount, date, description, categoryId, walletId);
		this.transactionId = transactionId;
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

	public LocalDateTime getDate() {
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
}
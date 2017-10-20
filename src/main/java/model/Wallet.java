package model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Wallet {
	private long wallettID;
	private String name;
	private BigDecimal amount;
	private long userId;
	private List<Transaction> transactions;
	
	public Wallet(String name, BigDecimal amount, long userId, List<Transaction> transactions) {
		this.name = name;
		this.amount = amount;
		this.userId = userId;
		this.transactions = new ArrayList<>();
	}

	public long getWallettID() {
		return wallettID;
	}

	public void setWallettID(long wallettID) {
		this.wallettID = wallettID;
	}

	public String getName() {
		return name;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public long getUserId() {
		return userId;
	}

	public List<Transaction> getTransactions() {
		return Collections.unmodifiableList(transactions);
	}
}

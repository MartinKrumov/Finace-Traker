package model;

import java.math.BigDecimal;
import java.util.*;

public class Wallet {
	private long wallettID;
	private String name;
	private BigDecimal amount;
	private long userId;
	private Set<Category> categories;
	
	public Wallet(String name, BigDecimal amount, long userId, Set<Category> categories) {
		this.name = name;
		this.amount = amount;
		this.userId = userId;
		this.categories = new HashSet<>();
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

	public Set<Category> getTransactions() {
		return Collections.unmodifiableSet(categories);
	}
}

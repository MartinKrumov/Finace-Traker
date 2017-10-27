package com.example.model.pojo;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Wallet implements Comparable<Wallet>{
	@Override
	public int compareTo(Wallet o) {
		return this.wallettID - o.wallettID;
	}

	private int wallettID;
	private String name;
	private BigDecimal amount;
	private int userId;
	private Set<Category> categories;
	
	public Wallet(String name, BigDecimal amount, int userId, Set<Category> categories) {
		this.name = name;
		this.amount = amount;
		this.userId = userId;
		this.categories = new HashSet<Category>();
	}
	public Wallet(String name, BigDecimal amount, int userId) {
		this.name = name;
		this.amount = amount;
		this.userId = userId;
		this.categories = new HashSet<Category>();
	}
	public long getWallettID() {
		return wallettID;
	}

	public void setWallettID(int wallettID) {
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

package model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Wallet {
	private long accaountID;
	private String name;
	private BigDecimal amount;
	private int userID;
	private List<Transaction> transactions;
	private List<Budget> budgets;
	
	public Wallet(String name, BigDecimal amount, int userID, List<Transaction> transactions, List<Budget> budgets) {
		this.name = name;
		this.amount = amount;
		this.userID = userID;
		this.transactions = transactions;
		this.budgets = budgets;
	}
	
	public Wallet(String name, BigDecimal amount, int userID) {
		this(name, amount, userID, new ArrayList<Transaction>(), new ArrayList<Budget>());
	}
}

package model;

import java.util.List;

public class CustomCategory {
	private long customCategoryId;
	private String name;
	private TransactionType type;
	private long userID;
	private List<Transaction> transactions;
	private List<Budget> budgets;
	
	public CustomCategory(String name, TransactionType type, long userID) {
		this.name = name;
		this.type = type;
		this.userID = userID;
	}
	
	public CustomCategory(long customCategoryId, String name, long userID, TransactionType type, List<Transaction> transactions, List<Budget> budgets) {
		this(name, type, userID, transactions, budgets);
		
		this.customCategoryId = customCategoryId;
	}
	
	public CustomCategory(String name, TransactionType type, long user, List<Transaction> transactions, List<Budget> budgets) {
		this.name = name;
		this.type = type;
		this.userID = user;
		this.transactions = transactions;
		this.budgets = budgets;
	}
	
	
}

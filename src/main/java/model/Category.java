package model;

import java.util.List;

public class Category {
	private long categoryID;
	private String name;
	private TransactionType type;
	private List<Transaction> transactions;
	private List<Budget> budgets;
	
	public Category(String name, TransactionType type, List<Transaction> transactions, List<Budget> budgets) {
		this.name = name;
		this.type = type;
		this.transactions = transactions;
		this.budgets = budgets;
	}
	
	
}

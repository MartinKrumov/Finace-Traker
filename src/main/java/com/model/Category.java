package com.model;

import java.util.Collections;
import java.util.List;

public class Category {
	private long categoryId;
	private String name;
	private TransactionType type;
    private String imagePath;
	private boolean isActive;
	private long userId;
	private long walletId;
    private List<Budget> budgets;
    private List<Transaction> transactions;

    public Category(String name, TransactionType type, String imagePath, boolean isActive) {
        this.name = name;
        this.type = type;
        this.imagePath = imagePath;
        this.isActive = isActive;
    }

    public Category(String name, TransactionType type , String imagePath, long userId, List<Budget> budgets) {
        this.name = name;
        this.type = type;
        this.imagePath = imagePath;
        this.isActive = true;
        this.userId = new Long(userId);
        this.budgets = budgets;
    }

    public long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public TransactionType getType() {
        return type;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public List<Budget> getBudgets() {
        return Collections.unmodifiableList(budgets);
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }
    
    
}

package com.example.model.pojo;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Category implements Comparable< Category >, Serializable {
    private long categoryId;
    private String name;
    private TransactionType type;
    private String imagePath;
    private long userId;
    private long walletId;
    private List< Budget > budgets;
    private Set< Transaction > transactions;

    public Category(long categoryId, String name, TransactionType type, String imagePath, long walletId, long userId, Set< Transaction > transactions) {
        this(categoryId, name, type, imagePath);
        this.walletId = walletId;
        this.userId = userId;
        this.transactions = new TreeSet<>();
        this.transactions = transactions;
    }

    public Category(long categoryId, String name, TransactionType type, String imagePath, long userId) {
        this(categoryId, name, type, imagePath);
        this.userId = userId;
        this.budgets = budgets;
        this.transactions = new TreeSet<>();
    }

    public Category(long categoryId, String name, TransactionType type, String imagePath) {
        this.categoryId = categoryId;
        this.name = name;
        this.type = type;
        this.imagePath = imagePath;
    }
    public Category( String name, TransactionType type) {
        this.name = name;
        this.type = type;
    }

    public long getWalletId() {
        return walletId;
    }

    public void setWalletId(long walletId) {
        this.walletId = walletId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setBudgets(List< Budget > budgets) {
        this.budgets = budgets;
    }

    public Set< Transaction > getTransactions() {
        return transactions;
    }

    public void setTransactions(Set< Transaction > transactions) {
        this.transactions = transactions;
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

    public List< Budget > getBudgets() {
        return Collections.unmodifiableList(budgets);
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getImagePath() {
        return imagePath;
    }


    @Override
    public int compareTo(Category o) {
        return ( int ) (this.categoryId - o.categoryId);
    }
}

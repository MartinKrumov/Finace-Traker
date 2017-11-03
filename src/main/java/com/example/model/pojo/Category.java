package com.example.model.pojo;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class Category implements Comparable< Category > ,Serializable{
    private int categoryId;
    private String name;
    private TransactionType type;
    private String imagePath;
    private String isActive;
    private int userId;
    private List< Budget > budgets;
    private List< Transaction > transactions;

    public Category(int categoryId, String name, TransactionType type, String imagePath, String isActive, int userId) {
        this.categoryId = categoryId;
        this.name = name;
        this.type = type;
        this.imagePath = imagePath;
        this.isActive = isActive;
        this.userId = userId;
    }

    public Category(String name, TransactionType type, String imagePath, int userId, List< Budget > budgets) {
        this.name = name;
        this.type = type;
        this.imagePath = imagePath;
        this.userId = userId;
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

    public List< Budget > getBudgets() {
        return Collections.unmodifiableList(budgets);
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String isActive() {
        return isActive;
    }

    @Override
    public int compareTo(Category o) {
        return this.categoryId - o.categoryId;
    }
}

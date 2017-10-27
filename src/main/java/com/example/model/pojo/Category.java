package com.example.model.pojo;

import java.util.Collections;
import java.util.List;

public class Category {
    private int categoryId;
    private String name;
    private TransactionType type;
    private String imagePath;
    private String isActive;
    private int userId;
    private int walletId;
    private List< Budget > budgets;
    private List< Transaction > transactions;

    public Category(String name, TransactionType type, String imagePath, String isActive, int walletId, int userId) {
        this.name = name;
        this.type = type;
        this.imagePath = imagePath;
        this.isActive = isActive;
        this.walletId = walletId;
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

    public long getWalletId() {
        return walletId;
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;

        Category category = ( Category ) o;

        if ( categoryId != category.categoryId ) return false;
        if ( userId != category.userId ) return false;
        if ( walletId != category.walletId ) return false;
        if ( !name.equals(category.name) ) return false;
        if ( type != category.type ) return false;
        if ( !imagePath.equals(category.imagePath) ) return false;
        return isActive.equals(category.isActive);
    }

    @Override
    public int hashCode() {
        int result = ( int ) (categoryId ^ (categoryId >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + imagePath.hashCode();
        result = 31 * result + isActive.hashCode();
        result = 31 * result + ( int ) (userId ^ (userId >>> 32));
        result = 31 * result + ( int ) (walletId ^ (walletId >>> 32));
        return result;
    }
}

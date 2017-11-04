package com.example.model.pojo;

import org.springframework.context.annotation.Bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

public class Wallet implements Comparable< Wallet >, Serializable {

    private long wallettID;
    private String name;
    private BigDecimal amount;
    private long userId;
    private Set< Category > categories;


    public Wallet() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Wallet(long wallettID, String name, BigDecimal amount, long userId, Set< Category > categories) {
        this.wallettID = wallettID;
        this.name = name;
        this.amount = amount;
        this.userId = userId;
        this.categories = new TreeSet< Category >();
    }

    public void setCategories(Set< Category > categories) {
        this.categories = categories;
    }

    public Wallet(long wallettID, String name, BigDecimal amount, long userId) {
        this.wallettID = wallettID;
        this.name = name;
        this.amount = amount;
        this.userId = userId;
        this.categories = new TreeSet< Category >();
    }

    public Set< Category > getCategories() {
        return categories;
    }

    public long getWallettID() {
        return wallettID;
    }

    public void setWallettID(int wallettID) {
        this.wallettID = wallettID;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }

    public Set< Category > getTransactions() {
        return Collections.unmodifiableSet(categories);
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public int compareTo(Wallet o) {
        return (int)(this.wallettID - o.wallettID);
    }

}

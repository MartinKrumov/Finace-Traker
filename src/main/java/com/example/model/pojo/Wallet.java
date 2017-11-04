package com.example.model.pojo;

import org.springframework.context.annotation.Bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

public class Wallet implements Comparable< Wallet >, Serializable {

    private long walletId;
    private String name;
    private BigDecimal amount;
    private long userId;
    private Set< Category > categories;
    private List<Transaction> transactions;


    public Wallet() {
    }

    public Wallet(long walletId, String name, BigDecimal amount, long userId, Set< Category > categories) {
        this.walletId = walletId;
        this.name = name;
        this.amount = amount;
        this.userId = userId;
        this.categories = new TreeSet< Category >();
    }

    public Wallet(long walletId, String name, BigDecimal amount, long userId) {
        this.walletId = walletId;
        this.name = name;
        this.amount = amount;
        this.userId = userId;
        this.categories = new TreeSet< Category >();
    }

    public Wallet(String name, BigDecimal amount, long userId,  List<Transaction> transactions) {
        this.name = name;
        this.amount = amount;
        this.userId = userId;
        this.transactions = transactions;
    }

    public Wallet(String name, BigDecimal amount, long userId) {
        this.name=name;
        this.amount=amount;
        setUserId(userId);
        this.transactions = new ArrayList<>();
        this.categories = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategories(Set< Category > categories) {
        this.categories = categories;
    }

    public Set< Category > getCategories() {
        return categories;
    }

    public long getWalletId() {
        return walletId;
    }

    public void setWalletId(long wallettID) {
        this.walletId = wallettID;
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
        return (int)(this.walletId - o.walletId);
    }

}

package com.example.model.pojo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

public class User implements Serializable, Comparable< User >{
    private long userId;
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private Date date;
    private int blocked;
    private int rights;
    private Set< Wallet > wallets;
    private Set< Category > ownCategories;
    private Set< Transaction > userTransaction;

    public User() {

    }

    public User(long userId, String username, String email, String firstName, String lastName, int blocked, int rights, Date date) {
        this.userId = userId;
        setUsername(username);
        setEmail(email);
        setFirstName(firstName);
        this.lastName = lastName;
        this.blocked = blocked;
        this.rights = rights;
        this.date = date;
        this.wallets = new TreeSet< Wallet >();
        this.ownCategories = new TreeSet<>();
        this.userTransaction = new TreeSet<>();
    }

    public Set< Transaction > getUserTransaction() {
        return userTransaction;
    }

    public void setUserTransaction(Set< Transaction > userTransaction) {
        this.userTransaction = userTransaction;
    }

    public void setUsername(String username) {
        if ( checkString(username) ) {
            this.username = username;
        }
    }

    public void setPassword(String password) {
        if ( checkString(password) ) {
            this.password = password;
        }
    }

    public void setFirstName(String firstName) {
        if ( checkString(firstName) ) {
            this.firstName = firstName;
        }
    }

    public void setLastName(String lastName) {
        if ( checkString(lastName) ) {
            this.lastName = lastName;
        }
    }

    public void setEmail(String email) {
        if ( checkString(email) ) {
            this.email = email;
        }
    }

    public void setBlocked(int blocked) {
        this.blocked = blocked;
    }

    public int getBlocked() {
        return blocked;
    }

    public int getRights() {
        return rights;
    }

    public void setRights(int rights) {
        this.rights = rights;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setWallets(Set< Wallet > wallets) {
        if ( !wallets.isEmpty() ) {
            this.wallets = wallets;
        }
    }

    public long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Set< Wallet > getWallets() {
        return Collections.unmodifiableSet(wallets);
    }

    public Date getDate() {
        return date;
    }

    public Set< Category > getOwnCategories() {
        return Collections.unmodifiableSet(ownCategories);
    }

    public void addOwnCategory(Category c) {
        this.ownCategories.add(c);
    }

    public boolean checkString(String str) {
        return str != null && !str.isEmpty();
    }

    @Override
    public int compareTo(User o) {
        return ( int ) (this.userId - o.userId);
    }
}

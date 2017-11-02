package com.example.model.pojo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class User implements Serializable{
    private long userId;
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private LocalDateTime date;
    private int blocked;
    private int rights;
    private Set< Wallet > wallets;
    private Set< Category > ownCategories;
    public User() {

    }
    public User(long userId, String username, String email, String firstName, String lastName, int blocked, int rights, LocalDateTime date) {
        this.userId = userId;
        setUsername(username);
        setEmail(email);
        setFirstName(firstName);
        this.lastName = lastName;
        this.blocked = blocked;
        this.rights = rights;
        this.date = date;
        this.wallets = new TreeSet< Wallet >();
        this.ownCategories = new HashSet<>();
    }
    public void setUsername(String username) {
        if ( checkString(username)) {
            this.username = username;
        }
    }

    public void setPassword(String password) {
        if ( checkString(password)) {
            this.password = password;
        }
    }

    public void setFirstName(String firstName) {
        if (  checkString(firstName)) {
            this.firstName = firstName;
        }
    }

    public void setLastName(String lastName) {
        if (  checkString(lastName)) {
            this.lastName = lastName;
        }
    }

    public void setEmail(String email) {
        if (  checkString(email)) {
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
        if(!wallets.isEmpty()) {
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

    public LocalDateTime getDate() {
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
}

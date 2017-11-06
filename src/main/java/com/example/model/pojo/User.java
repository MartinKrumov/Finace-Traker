package com.example.model.pojo;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

public class User implements Serializable, Comparable< User >{

    private long userId;

    @NotNull
    @Size(min = 3, max = 45)
    @NotEmpty
    @Pattern(regexp = "[^\\s]+")
    private String username;

    @NotNull
    @NotEmpty
    private String password;

    @NotNull
    @NotEmpty
    @Email
    @Pattern(regexp = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$")
    private String email;

    @NotNull
    @Size(min = 3, max = 20)
    @NotEmpty
    @Pattern(regexp = "[^\\s]+")
    private String firstName;

    @NotNull
    @Size(min = 3, max = 20)
    @NotEmpty
    @Pattern(regexp = "[^\\s]+")
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

    public void setPassword(String password) {
        if ( checkString(password) ) {
            this.password = password;
        }
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName.trim();
    }

    public void setLastName(String lastName) {
        this.lastName = lastName.trim();
    }

    public void setUsername(String username) {
        this.username = username.trim();
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

    public void setOwnCategories(Set< Category > ownCategories) {
        this.ownCategories = ownCategories;
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

package com.example.model;

import com.example.model.pojo.Category;
import com.example.model.pojo.Wallet;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class User {
    private int userId;
    //    @NotBlank
//    @Length( min = 3, max = 128 )
    private String username;
    //    @NotBlank
//    @Length( min = 6, max = 128 )
    private String password;
    //    @NotBlank
//    @Length( max = 128 )
//    @Email
    private String email;
    //    @NotBlank
//    @Length( min = 1, max = 128 )
    private String firstName;
    //    @NotBlank
//    @Length( min = 1, max = 128 )
    private String lastName;
    private String profilePic;
    private LocalDateTime date;
    private int blocked;
    private int rights;
    private Set< Wallet > wallets;
    private Set< Category > ownCategories;



    public User(int userId, String username, String email, String firstName, String lastName, String profilePic, int blocked, int rights, LocalDateTime date) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.profilePic = profilePic;
        this.blocked = blocked;
        this.rights = rights;
        this.date = date;
        this.wallets = new HashSet< Wallet >();
        this.ownCategories = new HashSet<>();
    }

    public User() {

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

    public String getProfilePic() {
        return profilePic;
    }

    public Set< Wallet > getWallets() {
        return Collections.unmodifiableSet(wallets);
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setUsername(String username) {
        if ( username != null && !username.isEmpty() ) {
            this.username = username;
        }
    }

    public void setPassword(String password) {
        if ( password != null && !password.isEmpty() ) {
            this.password = password;
        }
    }

    public void setFirstName(String firstName) {
        if ( firstName != null && !firstName.isEmpty() ) {
            this.firstName = firstName;
        }
    }

    public void setLastName(String lastName) {
        if ( lastName != null && !lastName.isEmpty() ) {
            this.lastName = lastName;
        }
    }

    public void setEmail(String email) {
        if ( email != null && !email.isEmpty() ) {
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
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public Set< Category > getOwnCategories() {
        return Collections.unmodifiableSet(ownCategories);
    }

    public void addOwnCategory(Category c) {
        this.ownCategories.add(c);
    }
}

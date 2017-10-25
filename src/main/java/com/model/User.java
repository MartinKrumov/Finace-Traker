package com.model;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class User {
	private long userId;
	private String username;
	private String password;
	private String email;
	private String firstName;
	private String lastName;
	private String profilePic;
	private LocalDateTime date;
	private Set<Wallet> wallets;
	private Set<Category> ownCategories;
	
	public User(String username, String pass, String email, String firstName, String lastName) {
		this.username = username;
		this.password = new String(pass);
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.wallets = new HashSet<Wallet>();
		this.ownCategories = new HashSet<>();
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

	public Set<Wallet> getWallets() {
		return Collections.unmodifiableSet(wallets);
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	public Set<Category> getOwnCategories() {
		return Collections.unmodifiableSet(ownCategories);
	}

	public void addOwnCategory(Category c) {
		this.ownCategories.add(c);
	}
}

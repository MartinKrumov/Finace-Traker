package model;

import java.awt.Image;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class User {
	private long userId;
	private String username;
	private char[] pass;
	private String email;
	private String firstName;
	private String lastName;
	private Image profilePic;
	private Set<Wallet> wallets;
	
	public User(String username, char[] pass, String email, String firstName, String lastName) {
		this.username = username;
		this.pass = pass;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.wallets = new HashSet<>();
	}

	public long getUserId() {
		return userId;
	}

	public String getUsername() {
		return username;
	}

	public char[] getPass() {
		return pass;
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

	public Image getProfilePic() {
		return profilePic;
	}

	public Set<Wallet> getWallets() {
		return Collections.unmodifiableSet(wallets);
	}
	
	public void setUserId(long userId) {
		this.userId = userId;
	}

	public void setProfilePic(Image profilePic) {
		this.profilePic = profilePic;
	}
	
}

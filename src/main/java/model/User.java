package model;

import java.util.Set;

public class User {
	private long userId;
	private String username;
	private String password;
	private String email;
	private String firstName;
	private String lastName;
	
	public User(String username, String password, String email, String firstName, String lastName) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
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
	
}

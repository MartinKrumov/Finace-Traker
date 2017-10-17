package model;

import java.util.Set;

public class User {
	private long userId;
	private String pass;
	private String email;
	private String firstName;
	private String lastName;
	
	public User(String firstName, String lastName, String email ,String pass) {
		this.pass = pass;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public long getUserId() {
		return userId;
	}

	public String getpass() {
		return pass;
	}

//	public String getPass() {
//		return pass;
//	}

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

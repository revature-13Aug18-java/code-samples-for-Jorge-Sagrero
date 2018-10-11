package com.project0.model;


public class UserCredentials  {

	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String loggedIn;
	private UserAccount accountNumber;
	
	public UserCredentials() {
		super();
	}
	public UserCredentials(String username, String password, String firstName, String lastName, String loggedIn,
			UserAccount accountNumber) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.loggedIn = loggedIn;
		this.accountNumber = accountNumber;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getLoggedIn() {
		return loggedIn;
	}
	public void setLoggedIn(String loggedIn) {
		this.loggedIn = loggedIn;
	}
	public UserAccount getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(UserAccount accountNumber) {
		this.accountNumber = accountNumber;
	}
	@Override
	public String toString() {
		return "UserCredentials [username=" + username + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", loggedIn=" + loggedIn + ", accountNumber=" + accountNumber + "]";
	}
	

}

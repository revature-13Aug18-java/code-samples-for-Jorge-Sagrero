package com.project1.model;

import java.util.List;

public class UserLog {
	/*EMAIL VARCHAR2(50), PSWRD VARCHAR2(50),FIRSTNAME VARCHAR2(50),
	LASTNAME VARCHAR2(50), PHONENUMBER NUMBER(10,0), ADDRESS VARCHAR2(50), MANAGER NUMBER(1,0),
	LOGGEDIN VARCHAR2(7),ACCOUNTNUMBER NUMBER(3,0) CONSTRAINT PK_USERLOGID PRIMARY KEY */
	private String email;
	private String pswrd;
	private String firstName;
	private String lastName;
	private long phoneNumber;
	private String address;
	private int manager;
	private String loggedIn;
	private List<Reimbursements> accountNumber;
	
	
	public UserLog() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPswrd() {
		return pswrd;
	}


	public void setPswrd(String pswrd) {
		this.pswrd = pswrd;
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


	public long getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public int getManager() {
		return manager;
	}


	public void setManager(int manager) {
		this.manager = manager;
	}


	public String getLoggedIn() {
		return loggedIn;
	}


	public void setLoggedIn(String loggedIn) {
		this.loggedIn = loggedIn;
	}


	public List<Reimbursements> getAccountNumber() {
		return accountNumber;
	}


	public void setAccountNumber(List<Reimbursements> accountNumber) {
		this.accountNumber = accountNumber;
	}


	public UserLog(String email, String pswrd, String firstName, String lastName, long phoneNumber, String address,
			int manager, String loggedIn, List<Reimbursements> accountNumber) {
		super();
		this.email = email;
		this.pswrd = pswrd;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.manager = manager;
		this.loggedIn = loggedIn;
		this.accountNumber = accountNumber;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountNumber == null) ? 0 : accountNumber.hashCode());
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((loggedIn == null) ? 0 : loggedIn.hashCode());
		result = prime * result + manager;
		result = prime * result + (int) (phoneNumber ^ (phoneNumber >>> 32));
		result = prime * result + ((pswrd == null) ? 0 : pswrd.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserLog other = (UserLog) obj;
		if (accountNumber == null) {
			if (other.accountNumber != null)
				return false;
		} else if (!accountNumber.equals(other.accountNumber))
			return false;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (loggedIn == null) {
			if (other.loggedIn != null)
				return false;
		} else if (!loggedIn.equals(other.loggedIn))
			return false;
		if (manager != other.manager)
			return false;
		if (phoneNumber != other.phoneNumber)
			return false;
		if (pswrd == null) {
			if (other.pswrd != null)
				return false;
		} else if (!pswrd.equals(other.pswrd))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "UserLog [email=" + email + ", pswrd=" + pswrd + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", phoneNumber=" + phoneNumber + ", address=" + address + ", manager=" + manager + ", loggedIn="
				+ loggedIn + ", accountNumber=" + accountNumber + "]";
	}
	
	
	
	
}

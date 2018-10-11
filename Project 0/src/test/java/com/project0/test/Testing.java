package com.project0.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.project0.credentials;

class Testing {
	static credentials defaultUser = new credentials();


	@Test
	public void returnsFalseWithInvalidEmail() {
		boolean check = defaultUser.validateUsername("shoottheshit");
		assertEquals(check,false);
	}
	
	@Test
	public void returnsTrueWhenValidEmailIsReceived() {
		boolean check = defaultUser.validateUsername("revatureisawesome@gmail.com");
		assertEquals(check,true);
	}
	
	@Test
	public void returnsFalseIfTheDataBaseIsNotEmpty() {
		boolean check = defaultUser.checkBankEmpty();
		assertEquals(check,false);
	}
	
	@Test
	public void returnsTrueIfTheUserWithPasswordIsInTheDatabase() {
		boolean check = defaultUser.validateUser("JSAGREROPEREZ@GMAIL.COM", "PASSWORD123");
		assertEquals(check,true);
	}
	
	@Test
	public void returnsFalseIfTheUserAndPasswordDoNotMatch() {
		boolean check = defaultUser.validateUser("flowercakes@ymail.com", "PASSWORD");
		assertEquals(check,false);
	}
	
	@Test
	public void returnsFalseIfThePasswordIsToShort() {
		boolean check = defaultUser.validPassword("small");
		assertEquals(check,false);
	}
	
	@Test
	public void returnsTrueIfTheUsernameIsAvailable() {
		boolean check = defaultUser.validateUsername("Revature@gmail.com");
		assertEquals(check,true);
	}
	
	@Test
	public void returnsFalseIfTheUsernameIsAlreadyInUse() {
		boolean check = defaultUser.validateUsername("JSAGREROPEREZ@GMAIL.COM");
		assertEquals(check,false);
	}
	

}

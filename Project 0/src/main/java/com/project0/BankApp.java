package com.project0;


import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.project0.dao.UserAccountDao;
import com.project0.dao.UserAccountDaoImpl;
import com.project0.dao.UserCredentialsDao;
import com.project0.dao.UserCredentialsDaoImpl;
import com.project0.model.UserCredentials;
import com.project0.util.ConnectionUtil;

public class BankApp {
	private static Logger log = Logger.getRootLogger();

	static credentials defaultUser = new credentials();
	public static void main(String[] args)  {
		
		Scanner input = new Scanner(System.in);
		Display menu = new Display();
		int userInput = -1;
		boolean check = false;
		do {
			userInput = defaultUser.validInt(2);
			
			switch (userInput) {
			case 2: {
				log.info("Hello, and Thank you for choosing Blah Banking.");
				defaultUser.newUser();
			}
			case 1: {
				String usrName = new String();
				String psw = new String();
				check = false;
				if(defaultUser.checkBankEmpty()) {
					log.info("There are no users registered at this bank!\\n Please enroll by choosing option 2.");
					break;
				}
				do {
					log.info("Please enter your Username: ");
					usrName = input.nextLine();
					log.info("Please enter your Password: ");
					psw = input.nextLine();
					check = defaultUser.validateUser(usrName, psw);
					
					
				}while(check == false);
				
				
				userInput = -1;
				do {
					userInput = defaultUser.validInt(3);
switch(userInput) {
					
						case 1: {
							double num = defaultUser.number("How much would you like to deposit?");
							defaultUser.depositMonies(num);
							break;
						}
						case 2: {
							double num = defaultUser.number("How much would you like to withdraw?");
							defaultUser.withdrawMonies(num);
							break;
						}
						case 3: {
							defaultUser.viewBalance();
							break;
						}
						case 0: {
							defaultUser.loggout();
							break;
						}
					}
						
						
			}while(userInput != 0);
				log.info("Logging you out...");
				
			}
			case 0: {
				
			}
				}
		
	}while(userInput != 0);
		log.info("Loggout complete, have a nice day.");

}
}

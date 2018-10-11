package com.project0;



import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.project0.dao.UserAccountDao;
import com.project0.dao.UserAccountDaoImpl;
import com.project0.dao.UserCredentialsDao;
import com.project0.dao.UserCredentialsDaoImpl;
import com.project0.model.UserAccount;
import com.project0.model.UserCredentials;
import com.project0.util.ConnectionUtil;

import oracle.net.aso.u;

public class credentials {
	private static Logger log = Logger.getRootLogger();

	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private double accountBalance;
	private String loggedIn;
	private int accountNumber;
	
	
	public credentials() {
		super();
	}





	public credentials(String username, String password, String firstName, String lastName, double accountBalance,
			String loggedIn) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.accountBalance = accountBalance;
		this.loggedIn = loggedIn;
	}


	public String isLoggedIn() {
		return loggedIn;
	}





	public void setLoggedIn(String loggedIn) {
		this.loggedIn = loggedIn;
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


	public double getAccountBalance() {
		return accountBalance;
	}


	public void setAccountBalance(int accountBalance) {
		this.accountBalance = accountBalance;
	}



	



	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(accountBalance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + accountNumber;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((loggedIn == null) ? 0 : loggedIn.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		credentials other = (credentials) obj;
		if (Double.doubleToLongBits(accountBalance) != Double.doubleToLongBits(other.accountBalance))
			return false;
		if (accountNumber != other.accountNumber)
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
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}


	public boolean validateNewUser(String usrName) {
		
		Connection connection;
			try {
				connection = ConnectionUtil.getConnection();
				CallableStatement proc = connection.prepareCall("{ ? = call USERNAME_VALIDATE(?) }");
				proc.registerOutParameter(1, Types.VARCHAR);
				proc.setString(2, usrName);
				proc.execute();
				String returnValue = proc.getString(1);
				if(returnValue.equals("FALSE")) {
					log.info("The username " + usrName + " is already in use in the database");
					return false;
				}
				else if(returnValue.equals("TRUE")) {
					return true;
				}
			} catch (IOException | SQLException e) {
				log.info("EXCEPTION CAUGHT IN credentials.java, line 236");
			}
			log.info("The username and/or password do not match");
			return false;
			}



	public boolean validateUser(String usrName, String psw) {
		
			Connection connection;
				try {
					connection = ConnectionUtil.getConnection();
					CallableStatement proc = connection.prepareCall("{ ? = call VALIDATE_FULL(?,?) }");
					proc.registerOutParameter(1, Types.VARCHAR);
					proc.setString(2, usrName);
					proc.setString(3, psw);
					proc.execute();
					String returnValue = proc.getString(1);
					if(returnValue.equals("TRUE")) {
						UserCredentialsDao edi = new UserCredentialsDaoImpl();
						List<UserCredentials> allUserCredentials = edi.getUserCredentials();
						for(UserCredentials e: allUserCredentials) {
							if(usrName.equals(e.getUsername())) {
								this.username = e.getUsername();
								this.password = e.getPassword();
								this.accountNumber = e.getAccountNumber().getAccountNumber();
								this.firstName = e.getFirstName();
								this.lastName = e.getLastName();
								this.accountBalance = e.getAccountNumber().getAccountBalance();
								this.loggedIn = "TRUE";
								e.setLoggedIn("TRUE");
							}
						}
						if(this.loggedIn.equals("TRUE")) {
							log.info("Welcome " + (this.getFirstName()) + " " + (this.getLastName()));
							proc.close();
							return true; 
						}
					}
				} catch (IOException | SQLException e) {
					log.info("EXCEPTION CAUGHT IN credentials.java, line 236");
				}
				log.info("The username and/or password do not match");
				return false;
					
				}
	
	
	
	
	
	
	public int getAccountNumber() {
		return accountNumber;
	}





	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}





	public String input() {
		Scanner data = new Scanner(System.in);
		return data.nextLine();
	}
	
	public boolean checkBankEmpty() {
		Statement stmt = null;
		int most = 0;
	    String query = "SELECT COUNT(*) FROM USERCREDENTIALS";
	    	Connection con;
	        try {
	        	con = ConnectionUtil.getConnection();
				stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
		        while (rs.next()) {
		            most = rs.getInt("count(*)");
		        }
		    
			} catch (SQLException | IOException e) {
				log.info("EXCEPTION CAUGHT IN credentials.java, line 284");
			}
	        if(most < 1)
	        	return true;
	        else
	        	return false;
	}
	
	public int checkNextAccNum() {
		Statement stmt = null;
		int most = 0;
	    String query = "SELECT COUNT(*) FROM USERCREDENTIALS";
	    	Connection con;
	        try {
	        	con = ConnectionUtil.getConnection();
				stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
		        while (rs.next()) {
		            most = rs.getInt("count(*)");
		        }
		    
			} catch (SQLException | IOException e) {
				log.info("EXCEPTION CAUGHT IN credentials.java, line 305");
			}
	        most = most + 1;
	        return most;
	}
	
	public int validInt(int r) {
		Scanner input = new Scanner(System.in);
		Display menu = new Display();
		int userInput = -1;

		do
		{
			if(r ==2)
				menu.loginMenu();
			else
				menu.mainMenu();
			
			while(!(input.hasNextInt())) {
				log.info("Please enter a valid integer");
				menu.loginMenu();
				input.nextLine();

			}
			userInput = input.nextInt();
			if((userInput == 1) && (checkBankEmpty())) 
			{
				log.info("There are no users registered at this bank!\\n Please enroll by choosing option 2.");
				userInput = -1;
			}
		}while((userInput < 0) || (userInput > r));
		return userInput;
	}
	
	
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
		    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	
	public boolean validateUsername(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
}
	
	public void newUser() {
		Scanner input = new Scanner(System.in);
		String newusrName;
		String psw;		
		do {
			log.info("Please enter a valid Email ( It will serve as your Username ) : ");
			newusrName = input.nextLine();
		} while((!this.validateUsername(newusrName))||(!this.validateNewUser(newusrName)));
		newusrName = newusrName.replaceAll(" " , "");
		do {
			log.info("Please enter your Password ( Must be at least 6 characters long) :");
			psw = input.nextLine();
			
		
		} while(!this.validPassword(psw));
		psw = psw.replaceAll(" ", "");
		log.info("Thank you for opening an account with us today!\\n Please enter your first name:");
		String neam = this.input();
		log.info("Please enter your last name: ");
		String lastNeam = this.input();
		neam = neam.replaceAll(" ", "");
		lastNeam = lastNeam.replaceAll(" ", "");
		
		UserAccount userAccount = new UserAccount(0,checkNextAccNum());
		UserCredentials userCredentials = new UserCredentials(newusrName,psw,neam,lastNeam,"FALSE",userAccount);
		UserAccountDao ua = new UserAccountDaoImpl();
		UserCredentialsDao uc = new UserCredentialsDaoImpl();
		uc.createUserCredentials(userCredentials);
		ua.createUserAccount(userAccount);
		this.accountBalance = userAccount.getAccountBalance();
		this.accountNumber = userAccount.getAccountNumber();
		this.firstName = userCredentials.getFirstName();
		this.lastName = userCredentials.getLastName();
		this.loggedIn = userCredentials.getLoggedIn();
		this.username = userCredentials.getUsername();
		log.info("Please validate your new account by entering your login credentials.\n");

		
	}
	
	
	
	
	
	public boolean validPassword(String pswrd) {
		Scanner input = new Scanner(pswrd);
		String pswrdNew = input.next();
		if(pswrdNew.length() < 6)
		{
			log.info("That is an invalid password, password must be at least 6 characters long");
			return false;
		}
		else if(input.hasNext()) {
			if(!(input.nextLine().replaceAll(" ", "").equals("") ) ) {
				
				log.info("That is an invalid password, you entered no characters except for whitespace");
				return false;
			}
			else
				return true;
		}
		else
			return true;
		
	} 
	
	
	
	

	
	
	

	public double number(String message) {
		Scanner blah = new Scanner (System.in);
		double num;
		
		log.info((message));
			
		while(!(blah.hasNextDouble())) {
			log.info("Please enter a valid integer");
			log.info((message));
			blah.nextLine();
		}
		num = blah.nextDouble();
		return num;
	}
	
	public void loggout() {
		
		UserAccount userAccount = new UserAccount(this.accountBalance,this.accountNumber);
		UserCredentials userCredentials = new UserCredentials(this.username,this.password,this.firstName,this.lastName,"FALSE",userAccount);
		log.info((userCredentials));
		log.info((userAccount));
		UserAccountDao ua = new UserAccountDaoImpl();
		UserCredentialsDao uc = new UserCredentialsDaoImpl();

		ua.updateUserAccount(userAccount);

		uc.updateUserCredentials(userCredentials);

		

		
	}
	
	public void depositMonies(double money ) {
		Double toBeTruncated = new Double(money);

		Double truncatedDouble = BigDecimal.valueOf(toBeTruncated)
		    .setScale(2, RoundingMode.HALF_UP)
		    .doubleValue();
		this.accountBalance = this.accountBalance + truncatedDouble;
		toBeTruncated = new Double(this.accountBalance);
		this.accountBalance = BigDecimal.valueOf(toBeTruncated)
			    .setScale(2, RoundingMode.HALF_UP)
			    .doubleValue();
	}
	public void withdrawMonies(double money ) {
		if(money <0 )
		{
			log.info("You cannot withdraw a negative value");
			return;
		}
		if((this.getAccountBalance() - money) < 0) {
			log.info("Overdraft warning, You cannot withdraw more than your current balance which is " + (this.getAccountBalance()));
			return;
		}
		
		Double toBeTruncated = new Double(money);

		Double truncatedDouble = BigDecimal.valueOf(toBeTruncated)
		    .setScale(2, RoundingMode.HALF_UP)
		    .doubleValue();
		this.accountBalance = this.accountBalance - truncatedDouble;
		toBeTruncated = new Double(this.accountBalance);
		this.accountBalance = BigDecimal.valueOf(toBeTruncated)
			    .setScale(2, RoundingMode.HALF_UP)
			    .doubleValue();
	}
	public void viewBalance() {
		log.info("your current balance is " + (this.accountBalance));
	}
}




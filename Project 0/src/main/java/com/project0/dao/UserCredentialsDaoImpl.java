package com.project0.dao;


	import java.io.IOException;
	import java.sql.Connection;
	import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.ArrayList;
	import java.util.List;

	import org.apache.log4j.Logger;

import com.project0.model.UserAccount;
import com.project0.model.UserCredentials;
import com.project0.util.ConnectionUtil;



	public class UserCredentialsDaoImpl implements UserCredentialsDao {
		private static Logger log = Logger.getRootLogger();
		
		@Override
		public List<UserCredentials> getUserCredentials() {
			List<UserCredentials> userCredentialsList = new ArrayList<>();
			
			String sql = "SELECT * FROM USERCREDENTIALS";
			
			try (Connection con = ConnectionUtil.getConnection();
					Statement s = con.createStatement();
					ResultSet rs = s.executeQuery(sql)){
			
				while(rs.next()) {
					UserCredentials e = new UserCredentials();
					
					String userId = rs.getString("USERNAME");
					e.setUsername(userId);
					
					String pswrd = rs.getString("PSWRD");
					e.setPassword(pswrd);
					
					String fName = rs.getString("FIRSTNAME");
					e.setFirstName(fName);
					
					String lName = rs.getString("LASTNAME");
					e.setLastName(lName);
					
					String logged = rs.getString("LOGGEDIN");
					e.setLoggedIn(logged);
					
					int accountNumber= rs.getInt("ACCOUNTNUMBER");
					// call to the UserAccountDao's getUserAccountById method
					UserAccountDao ddi = new UserAccountDaoImpl();
					UserAccount d = ddi.getUserAccountById(accountNumber, con);
					e.setAccountNumber(d);
					
					userCredentialsList.add(e);
				}
				
			} catch (IOException|SQLException e) {
				log.error(e.getMessage());
			} 
			
			return userCredentialsList;
		}

		@Override
		public UserCredentials getUserCredentialsById(int id) {
			UserCredentials d = null;
			String sql = "SELECT * FROM USERCREDENTIALS WHERE ACCOUNTNUMBER = ?";

			ResultSet rs = null;

			try (Connection con = ConnectionUtil.getConnection();
					PreparedStatement ps = con.prepareStatement(sql);){

				ps.setInt(1, id);
				rs = ps.executeQuery();

				while (rs.next()) {
									
					String userId = rs.getString("USERNAME");
					
					String pswrd = rs.getString("PSWRD");
					
					String fName = rs.getString("FIRSTNAME");
					
					String lName = rs.getString("LASTNAME");
					
					String logged = rs.getString("LOGGEDIN");
					
					int accountNumber= rs.getInt("ACCOUNTNUMBER"); 

					UserAccountDao ddi = new UserAccountDaoImpl();
					UserAccount f = ddi.getUserAccountById(accountNumber, con);
					
					//finished creating variables that hold the specific user credentials to pass into constructor to return 
					d = new UserCredentials(userId, pswrd, fName, lName, logged, f);
				}
			} catch (IOException|SQLException e) {
				log.error(e.getMessage());
			} finally {
				if (rs!=null) {
					try {
						rs.close();
					} catch (SQLException e) {
						log.error(e.getMessage());
					}
				}
			}

			return d;
		}

		@Override
		public int createUserCredentials(UserCredentials userCredentials) {
			int userCredentialsCreated = 0;
			String sql = "INSERT INTO USERCREDENTIALS (USERNAME, PSWRD, FIRSTNAME, LASTNAME, LOGGEDIN, ACCOUNTNUMBER) VALUES (?,?,?,?,?,?)";
			
			try (Connection con = ConnectionUtil.getConnection();
					PreparedStatement ps = con.prepareStatement(sql)){
				
				ps.setString(1, userCredentials.getUsername());
				ps.setString(2, userCredentials.getPassword());
				ps.setString(3, userCredentials.getFirstName());
				ps.setString(4, userCredentials.getLastName());
				ps.setString(5, userCredentials.getLoggedIn());
				ps.setInt(6, userCredentials.getAccountNumber().getAccountNumber());

				
				userCredentialsCreated = ps.executeUpdate();
				//con.commit();
				
			} catch (SQLException|IOException e) {
				log.error(e.getMessage());
			} 
			return userCredentialsCreated;
		}

		@Override
		public int updateUserCredentials(UserCredentials userCredentials) {
			int userCredentialsUpdated = 0;
		
			
			String sql = "UPDATE USERCREDENTIALS "
					+ "SET  USERNAME = ?,"
					+ "PSWRD = ?,"
					+ "FIRSTNAME = ?,"
					+ "LASTNAME = ?,"
					+ "LOGGEDIN = ?"
					+ "WHERE ACCOUNTNUMBER = ?";
			
			try (Connection con = ConnectionUtil.getConnection();
					PreparedStatement ps = con.prepareStatement(sql)){
				
				con.setAutoCommit(false);
				ps.setString(1, userCredentials.getUsername());

				ps.setString(2, userCredentials.getPassword());

				ps.setString(3, userCredentials.getFirstName());

				ps.setString(4, userCredentials.getLastName());

				ps.setString(5, userCredentials.getLoggedIn());

				ps.setInt(6, userCredentials.getAccountNumber().getAccountNumber());

				userCredentialsUpdated = ps.executeUpdate();

				con.commit();
				
			} catch (SQLException|IOException e) {
				log.error(e.getMessage());
			} 
			
			return userCredentialsUpdated;
		}

		@Override
		public int deleteUserCredentialsById(int id) {
			int rowsDeleted = 0;
			
			String sql = "DELETE FROM USERCREDENTIALS WHERE ACCOUNTNUMBER = ?";
			
			try (Connection con = ConnectionUtil.getConnection();
					PreparedStatement ps = con.prepareStatement(sql)){
				
				ps.setInt(1, id);
				rowsDeleted = ps.executeUpdate();
				
			} catch (SQLException|IOException e) {
				log.error(e.getMessage());
			}
			
			return rowsDeleted;
		}

		@Override
		public UserCredentials getUserCredentialsById(int id, Connection con) {
			UserCredentials d = null;
			String sql = "SELECT * FROM USERCREDENTIALS WHERE ACCOUNTNUMBER = ?";

			ResultSet rs = null;

			try (PreparedStatement ps = con.prepareStatement(sql);){

				ps.setInt(1, id);
				rs = ps.executeQuery();

				while (rs.next()) {
					String userId = rs.getString("USERNAME");
					
					String pswrd = rs.getString("PSWRD");
					
					String fName = rs.getString("FIRSTNAME");
					
					String lName = rs.getString("LASTNAME");
					
					String logged = rs.getString("LOGGEDIN");
					
					int accountNumber= rs.getInt("ACCOUNTNUMBER"); 

					UserAccountDao ddi = new UserAccountDaoImpl();
					UserAccount f = ddi.getUserAccountById(accountNumber, con);
					
					d = new UserCredentials(userId, pswrd, fName, lName, logged, f);
				}
			} catch (SQLException e) {
				log.error(e.getMessage());
			} finally {
				if (rs!=null) {
					try {
						rs.close();
					} catch (SQLException e) {
						log.error(e.getMessage());
					}
				}
			}

			return d;
		}


}

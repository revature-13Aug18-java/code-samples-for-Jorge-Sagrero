package com.project1.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.project1.model.Reimbursements;
import com.project1.model.UserLog;
import com.project1.util.ConnectionUtil;



public class UserLogDaoImpl implements UserLogDao{
	
	private static Logger log = Logger.getRootLogger();
	
	@Override
	public List<UserLog> getUserLog() {
		List<UserLog> userLogList = new ArrayList<>();
		
		String sql = "SELECT * FROM USERLOG";
		
		try (Connection con = ConnectionUtil.getConnection();
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(sql)){
		
			while(rs.next()) {
				UserLog e = new UserLog();
				
				String email = rs.getString("EMAIL");
				e.setEmail(email);
				String pswrd = rs.getString("PSWRD");
				e.setPswrd(pswrd);
				String firstName = rs.getString("FIRSTNAME");
				e.setFirstName(firstName);
				String lastName = rs.getString("LASTNAME");
				e.setLastName(lastName);
				long phoneNumber = rs.getLong("PHONENUMBER");
				e.setPhoneNumber(phoneNumber);
				String address = rs.getString("ADDRESS");
				e.setAddress(address);
				int manager = rs.getInt("MANAGER");
				e.setManager(manager);
				String loggedIn = rs.getString("LOGGEDIN");
				e.setLoggedIn(loggedIn);
				int accountNumber = rs.getInt("ACCOUNTNUMBER");
				ReimbursementsDao ddi = new ReimbursementsDaoImpl();
				List<Reimbursements> d = ddi.getReimbursementsById(accountNumber, con);
				e.setAccountNumber(d);
				
				userLogList.add(e);
			}
			
		} catch (IOException|SQLException e) {
			log.error(e.getMessage());
		} 
		
		return userLogList;
	}

	@Override
	public UserLog getUserLogById(int id) {
		UserLog d = null;
		String sql = "SELECT * FROM USERLOG WHERE ACCOUNTNUMBER = ?";

		ResultSet rs = null;

		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);){

			ps.setInt(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {
				String email = rs.getString("EMAIL");
				String pswrd = rs.getString("PSWRD");
				String firstName = rs.getString("FIRSTNAME");
				String lastName = rs.getString("LASTNAME");
				long phoneNumber = rs.getLong("PHONENUMBER");
				String address = rs.getString("ADDRESS");
				int manager = rs.getInt("MANAGER");
				String loggedIn = rs.getString("LOGGEDIN");
				int accountNumber = rs.getInt("ACCOUNTNUMBER");
				ReimbursementsDao ddi = new ReimbursementsDaoImpl();
				List<Reimbursements> f = ddi.getReimbursementsById(accountNumber, con);
				d = new UserLog(email,pswrd,firstName,lastName,phoneNumber,address,manager,loggedIn,f);
								
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
	public int createUserLog(UserLog userLog) {
		int userLogsCreated = 0;
		
		String sql = "INSERT INTO USERLOG (EMAIL, PSWRD, FIRSTNAME, LASTNAME, PHONENUMBER, ADDRESS, MANAGER, LOGGEDIN, ACCOUNTNUMBER) VALUES (?,?,?,?,?,?,?,?,?)";
		
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			
			
			ps.setString(1, userLog.getEmail());
			ps.setString(2, userLog.getPswrd());
			ps.setString(3, userLog.getFirstName());
			ps.setString(4, userLog.getLastName());
			ps.setLong(5, userLog.getPhoneNumber());
			ps.setString(6, userLog.getAddress());
			ps.setInt(7, userLog.getManager());
			ps.setString(8, userLog.getLoggedIn());
			ps.setInt(9, userLog.getAccountNumber().get(0).getAccountNumber());
			//make sure to initialize Reimbursment object for everyone

			
			userLogsCreated = ps.executeUpdate();
			//con.commit();
			
		} catch (SQLException|IOException e) {
			log.error(e.getMessage());
		} 
		return userLogsCreated;
	}

	@Override
	public int updateUserLog(UserLog userLog) {
		int userLogsUpdated = 0;

		
		String sql = "UPDATE USERLOG "
				+ "SET EMAIL = ?,"
				+ "PSWRD = ?,"
				+ "FIRSTNAME = ?,"
				+ "LASTNAME = ?,"
				+ "PHONENUMBER = ?,"
				+ "ADDRESS = ?,"
				+ "MANAGER = ?,"
				+ "LOGGEDIN = ?"
				+ "WHERE ACCOUNTNUMBER = ?";
		
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			
			con.setAutoCommit(false);
			ps.setString(1, userLog.getEmail());
			ps.setString(2, userLog.getPswrd());
			ps.setString(3, userLog.getFirstName());
			ps.setString(4, userLog.getLastName());
			ps.setLong(5, userLog.getPhoneNumber());
			ps.setString(6, userLog.getAddress());
			ps.setInt(7, userLog.getManager());
			ps.setString(8, userLog.getLoggedIn());
			ps.setInt(9, userLog.getAccountNumber().get(0).getAccountNumber());

			userLogsUpdated = ps.executeUpdate();

			con.commit();
			
		} catch (SQLException|IOException e) {
			log.error(e.getMessage());
		} 
		
		return userLogsUpdated;
	}

	@Override
	public int deleteUserLogById(int id) {
		int rowsDeleted = 0;
		
		String sql = "DELETE FROM USERLOG WHERE ACCOUNTNUMBER = ?";
		
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
	public UserLog getUserLogById(int id, Connection con) {
		UserLog d = null;
		String sql = "SELECT * FROM USERLOG WHERE ACCOUNTNUMBER = ?";

		ResultSet rs = null;

		try (PreparedStatement ps = con.prepareStatement(sql);){

			ps.setInt(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {
				
				String email = rs.getString("EMAIL");
				String pswrd = rs.getString("PSWRD");
				String firstName = rs.getString("FIRSTNAME");
				String lastName = rs.getString("LASTNAME");
				long phoneNumber = rs.getLong("PHONENUMBER");
				String address = rs.getString("ADDRESS");
				int manager = rs.getInt("MANAGER");
				String loggedIn = rs.getString("LOGGEDIN");
				int accountNumber = rs.getInt("ACCOUNTNUMBER");
				ReimbursementsDao ddi = new ReimbursementsDaoImpl();
				List<Reimbursements> f = ddi.getReimbursementsById(accountNumber, con);
				
				d = new UserLog(email, pswrd, firstName, lastName, phoneNumber, address, manager, loggedIn, f);
				
				
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

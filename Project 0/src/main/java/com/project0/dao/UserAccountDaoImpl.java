package com.project0.dao;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.project0.model.UserAccount;
import com.project0.util.ConnectionUtil;

public class UserAccountDaoImpl implements UserAccountDao{
	private static Logger log = Logger.getRootLogger();

	@Override
	public List<UserAccount> getUserAccounts() {
		List<UserAccount> accounts = new ArrayList<>();
		
		String sql = "SELECT * FROM USERACCOUNT";
		
		try (Connection con = ConnectionUtil.getConnection();
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(sql)){
			
			while (rs.next()) {
				double accountBalance = rs.getDouble("ACCOUNTBALANCE");
				int accountNumber = rs.getInt("ACCOUNTNUMBER");
				accounts.add(new UserAccount(accountBalance,accountNumber));
			}
			
			
		} catch (SQLException|IOException e) {
			log.error(e.getMessage());
		} 
		
		return accounts;
	}
	
	@Override
	public UserAccount getUserAccountById(int id) {
		UserAccount d = null;
		String sql = "SELECT * FROM USERACCOUNT WHERE ACCOUNTNUMBER = ?";

		ResultSet rs = null;

		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);){

			ps.setInt(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {
				double accountBalance = rs.getDouble("ACCOUNTBALANCE");
				int accountNumber = rs.getInt("ACCOUNTNUMBER");
				d = new UserAccount(accountBalance, accountNumber);
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
	
	public UserAccount getUserAccountById(int id, Connection con) {
		UserAccount d = null;
		String sql = "SELECT * FROM UserAccount WHERE ACCOUNTNUMBER = ?";

		ResultSet rs = null;

		try (PreparedStatement ps = con.prepareStatement(sql);){

			ps.setInt(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {
				double accountBalance = rs.getDouble("ACCOUNTBALANCE");
				int accountNumber = rs.getInt("ACCOUNTNUMBER");
				d = new UserAccount(accountBalance, accountNumber);
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
	@Override
	public int createUserAccount(UserAccount userAccount) {
		int accountsCreated = 0;
		String sql = "INSERT INTO USERACCOUNT (ACCOUNTBALANCE, ACCOUNTNUMBER) VALUES (?,?)";
		
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			
			ps.setDouble(1, userAccount.getAccountBalance());
			ps.setInt(2, userAccount.getAccountNumber());
			accountsCreated = ps.executeUpdate();
			//con.commit();
			
		} catch (SQLException|IOException e) {
			log.error(e.getMessage());
		} 
		return accountsCreated;
	}

	@Override
	public int updateUserAccount(UserAccount userAccount) {
		int accountsUpdated = 0;
		
		String sql = "UPDATE USERACCOUNT "
				+ "SET ACCOUNTBALANCE = ?"
				+ "WHERE ACCOUNTNUMBER = ?";
		
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			
			con.setAutoCommit(false);
			ps.setDouble(1, userAccount.getAccountBalance());
			ps.setInt(2, userAccount.getAccountNumber());
			accountsUpdated = ps.executeUpdate();
			con.commit();
			
		} catch (SQLException|IOException e) {
			log.error(e.getMessage());
		} 
		
		return accountsUpdated;
	}

	@Override
	public int deleteUserAccountById(int id) {
		int rowsDeleted = 0;
		
		String sql = "DELETE FROM USERACCOUNT WHERE ACCOUNTNUMBER = ?";
		
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
	public void increaseBalance(int id, double increaseAmount) {
		
		String sql = "{call DEPOSIT(?,?)}";
		
		try( Connection con = ConnectionUtil.getConnection();
				CallableStatement cs = con.prepareCall(sql)){
			
			cs.setInt(1, id);
			cs.setDouble(2, increaseAmount);
			cs.execute();
			log.info("Deposit processed");
		} catch (SQLException|IOException e) {
			log.error(e.getMessage());
		} 
	

}
}

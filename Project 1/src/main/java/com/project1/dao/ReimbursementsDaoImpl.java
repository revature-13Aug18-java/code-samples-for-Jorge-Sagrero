package com.project1.dao;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.project1.model.Reimbursements;
import com.project1.util.ConnectionUtil;





public class ReimbursementsDaoImpl implements ReimbursementsDao{
	
	private static Logger log = Logger.getRootLogger();

	@Override
	public List<Reimbursements> getReimbursements() {
		List<Reimbursements> reimbursements = new ArrayList<>();
		
		String sql = "SELECT * FROM REIMBURSEMENTS";
		
		try (Connection con = ConnectionUtil.getConnection();
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(sql)){
			
			while (rs.next()) {
				long receipt = rs.getLong("RECEIPT");
				BigDecimal amount = rs.getBigDecimal("AMOUNT");
				String reason = rs.getString("REASON");
				int status = rs.getInt("STATUS");
				int managerId = rs.getInt("MANAGERID");
				int accountNumber = rs.getInt("ACCOUNTNUMBER");
				
				reimbursements.add(new Reimbursements(receipt,amount,reason,status,managerId,accountNumber));
			}
			
			
		} catch (SQLException|IOException e) {
			log.error(e.getMessage());
		} 
		
		return reimbursements;
	}
	
	@Override
	public List<Reimbursements> getReimbursementsById(int id) {
		List<Reimbursements> d = new ArrayList<>();
		String sql = "SELECT * FROM REIMBURSEMENTS WHERE ACCOUNTNUMBER = ?";

		ResultSet rs = null;

		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);){

			ps.setInt(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {
				long receipt = rs.getLong("RECEIPT");
				BigDecimal amount = rs.getBigDecimal("AMOUNT");
				String reason = rs.getString("REASON");
				int status = rs.getInt("STATUS");
				int managerId = rs.getInt("MANAGERID");
				int accountNumber = rs.getInt("ACCOUNTNUMBER");
				d.add( new Reimbursements(receipt,amount,reason,status,managerId,accountNumber));
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
	
	public List<Reimbursements> getReimbursementsById(int id, Connection con) {
		List<Reimbursements> d = new ArrayList<>();
		String sql = "SELECT * FROM REIMBURSEMENTS WHERE ACCOUNTNUMBER = ?";

		ResultSet rs = null;

		try (PreparedStatement ps = con.prepareStatement(sql);){

			ps.setInt(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {
				long receipt = rs.getLong("RECEIPT");
				BigDecimal amount = rs.getBigDecimal("AMOUNT");
				String reason = rs.getString("REASON");
				int status = rs.getInt("STATUS");
				int managerId = rs.getInt("MANAGERID");
				int accountNumber = rs.getInt("ACCOUNTNUMBER");
				d.add(new Reimbursements(receipt,amount,reason,status,managerId,accountNumber));
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
	public int createReimbursements(Reimbursements reimbursements) {
		int reimbursementsCreated = 0;
		String sql = "INSERT INTO REIMBURSEMENTS (RECEIPT,AMOUNT,REASON,STATUS,MANAGERID,ACCOUNTNUMBER) VALUES (?,?,?,?,?,?)";
		
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			
			ps.setLong(1, reimbursements.getReceipt());
			ps.setBigDecimal(2,reimbursements.getAmount());
			ps.setString(3, reimbursements.getReason());
			ps.setInt(4, reimbursements.getStatus());
			ps.setInt(5, reimbursements.getManagerId());
			ps.setInt(6, reimbursements.getAccountNumber());
			reimbursementsCreated = ps.executeUpdate();
			//con.commit();
			
		} catch (SQLException|IOException e) {
			log.error(e.getMessage());
		} 
		return reimbursementsCreated;
	}

	@Override
	public int updateReimbursementsByReceipt(Reimbursements reimbursements) {
		int reimbursementsUpdated = 0;
	
		String sql = "UPDATE REIMBURSEMENTS "
				+ "SET STATUS = ?,"
				+ "MANAGERID = ?"
				+ "WHERE RECEIPT = ?";
		
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			con.setAutoCommit(false);
			ps.setInt(1, reimbursements.getStatus());
			ps.setInt(2, reimbursements.getManagerId());
			ps.setLong(3, reimbursements.getReceipt());
			reimbursementsUpdated = ps.executeUpdate();
			con.commit();
			
		} catch (SQLException|IOException e) {
			log.error(e.getMessage());
		} 
		
		return reimbursementsUpdated;
	}

	@Override
	public int deleteReimbursementsByReceipt(int id) {
		int rowsDeleted = 0;
		
		String sql = "DELETE FROM REIMBURSEMENTS WHERE RECEIPT = ?";
		
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
	public Reimbursements getReimbursementByReceipt(int id) {

		
		String sql = "SELECT * FROM REIMBURSEMENTS WHERE RECEIPT = ?";
		Reimbursements r = null;
		ResultSet rs = null;
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				long receipt = rs.getLong("RECEIPT");
				BigDecimal amount = rs.getBigDecimal("AMOUNT");
				String reason = rs.getString("REASON");
				int status = rs.getInt("STATUS");
				int managerId = rs.getInt("MANAGERID");
				int accountNumber = rs.getInt("ACCOUNTNUMBER");
				
				r = new Reimbursements(receipt,amount,reason,status,managerId,accountNumber);
			}
		} catch (SQLException|IOException e) {
			log.error(e.getMessage());
		}
		
		
		return r;
	}
	
	@Override
	public List<Reimbursements> getReimbursementByStatus(int id) {

		
		String sql = "SELECT * FROM REIMBURSEMENTS WHERE STATUS = ?";
		List<Reimbursements> reimbursements = new ArrayList<>();
		ResultSet rs = null;
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				long receipt = rs.getLong("RECEIPT");
				BigDecimal amount = rs.getBigDecimal("AMOUNT");
				String reason = rs.getString("REASON");
				int status = rs.getInt("STATUS");
				int managerId = rs.getInt("MANAGERID");
				int accountNumber = rs.getInt("ACCOUNTNUMBER");
				
				reimbursements.add(new Reimbursements(receipt,amount,reason,status,managerId,accountNumber));
			}
		} catch (SQLException|IOException e) {
			log.error(e.getMessage());
		}
		
		
		return reimbursements;
	}
	@Override
	public List<Reimbursements> getReimbursementByStatusAndId(int id, int stat) {

		
		String sql = "SELECT * FROM REIMBURSEMENTS WHERE STATUS = ? AND ACCOUNTNUMBER = ?";
		List<Reimbursements> reimbursements = new ArrayList<>();
		ResultSet rs = null;
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			
			ps.setInt(1, stat);
			ps.setInt(2, id);

			rs = ps.executeQuery();
			while (rs.next()) {
				long receipt = rs.getLong("RECEIPT");
				BigDecimal amount = rs.getBigDecimal("AMOUNT");
				String reason = rs.getString("REASON");
				int status = rs.getInt("STATUS");
				int managerId = rs.getInt("MANAGERID");
				int accountNumber = rs.getInt("ACCOUNTNUMBER");
				
				reimbursements.add(new Reimbursements(receipt,amount,reason,status,managerId,accountNumber));
			}
		} catch (SQLException|IOException e) {
			log.error(e.getMessage());
		}
		
		
		return reimbursements;
	}
	

}

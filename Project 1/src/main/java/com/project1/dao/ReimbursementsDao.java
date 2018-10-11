package com.project1.dao;

import java.sql.Connection;
import java.util.List;

import com.project1.model.Reimbursements;



public interface ReimbursementsDao {

	public List<Reimbursements> getReimbursements();
	public List<Reimbursements> getReimbursementsById(int id);
	public List<Reimbursements> getReimbursementsById(int id, Connection con);
	public Reimbursements getReimbursementByReceipt(int id);
	public int createReimbursements(Reimbursements user);
	public int updateReimbursementsByReceipt(Reimbursements user);
	public int deleteReimbursementsByReceipt(int id);
	List<Reimbursements> getReimbursementByStatus(int id);
	List<Reimbursements> getReimbursementByStatusAndId(int id, int Stat);
	
}

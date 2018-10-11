package com.project1.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.project1.dao.ReimbursementsDao;
import com.project1.dao.ReimbursementsDaoImpl;
import com.project1.dao.UserLogDao;
import com.project1.dao.UserLogDaoImpl;
import com.project1.model.Reimbursements;
import com.project1.model.UserLog;
import com.project1.util.ConnectionUtil;

public class Driver {
	public static void main(String[] args) {
		try {
			Connection con = ConnectionUtil.getConnection();
			System.out.println(con.getMetaData().getDriverName());
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		
		ReimbursementsDao d = new ReimbursementsDaoImpl();
		
		Reimbursements dd = d.getReimbursementByReceipt(1);
		System.out.println(d);
		System.out.println(dd);
		
		dd.setStatus(1);
		dd.setManagerId(1);
		System.out.println(dd);
		d.updateReimbursementsByReceipt(dd);
		dd = d.getReimbursementByReceipt(1);
		System.out.println(dd);
		System.out.println(d);
//		
//		UserLogDao edi = new UserLogDaoImpl();
//		List<UserLog> allEmployees = edi.getUserLog();
//		for(UserLog e: allEmployees) {
//			System.out.println(e);
//		}
	}

	
}

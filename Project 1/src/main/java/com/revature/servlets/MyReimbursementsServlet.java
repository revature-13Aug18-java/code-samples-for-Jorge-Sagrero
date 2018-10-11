package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project1.dao.ReimbursementsDao;
import com.project1.dao.ReimbursementsDaoImpl;
import com.project1.dao.UserLogDao;
import com.project1.dao.UserLogDaoImpl;
import com.project1.model.Reimbursements;
import com.project1.model.UserLog;

/**
 * Servlet implementation class MyReimbursementsServlet
 */
public class MyReimbursementsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);//if true was passed in, a session would have been created
		ObjectMapper om = new ObjectMapper();
		PrintWriter pw = response.getWriter();
		if( session!= null && session.getAttribute("accountNumber")!= null) {
			int accountNum = (int) session.getAttribute("accountNumber");
			UserLogDao u = new UserLogDaoImpl();
			UserLog userlogs = u.getUserLogById(accountNum);
			String userLogString = om.writeValueAsString(userlogs);
			userLogString = "{\"userLog\":"+userLogString+"}";
			pw.print(userLogString);
		} 
		else {
			pw.write("{\"userLog\": 0 }");
		}
		
		}	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * RECEIPT
AMOUNT
REASON
STATUS
MANAGERID
ACCOUNTNUMBER
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);//if true was passed in, a session would have been created
		if( session!= null && session.getAttribute("accountNumber")!= null) {
			BigDecimal amount = new BigDecimal(request.getParameter("amount")).setScale(2, RoundingMode.HALF_UP);
			String reason = request.getParameter("reason");
			int accountNum = (int) session.getAttribute("accountNumber");
			UserLogDao ed = new UserLogDaoImpl();
			List<UserLog> acc = ed.getUserLog();
			ReimbursementsDao r = new ReimbursementsDaoImpl();
			Reimbursements rr = new Reimbursements();
//			System.out.println("hello from my reimbursements");
//			System.out.println(r.getReimbursements().size());
//			System.out.println(ed.getUserLog().size());

			int receiptNumber = (r.getReimbursements().size()) - (ed.getUserLog().size())+ 1;
//			System.out.println("receiptNumber = " +receiptNumber);
			rr.setAccountNumber(accountNum);
			rr.setAmount(amount);
			rr.setReason(reason);
			rr.setReceipt(receiptNumber);
			rr.setStatus(0);
			rr.setManagerId(0);
			r.createReimbursements(rr);
			response.sendRedirect("/p1-Jorge-Sagrero-Perez/Views/myReimbursements.html");

		}
		else {
			response.sendRedirect("/p1-Jorge-Sagrero-Perez/Views/Login.html");
		}
	}
			
}

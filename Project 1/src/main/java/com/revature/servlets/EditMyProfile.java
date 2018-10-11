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
 * Servlet implementation class EditMyProfile
 */
public class EditMyProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditMyProfile() {
        super();
        // TODO Auto-generated constructor stub
    }
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
			userLogString = "{\"userLog\":"+userLogString+",\"manager\":"+ session.getAttribute("accountNumber") +"}";
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		HttpSession session = request.getSession(false);//if true was passed in, a session would have been created
		if( session!= null && session.getAttribute("accountNumber")!= null) {
			String fname = request.getParameter("fname");
			String lname = request.getParameter("lname");
			String email = request.getParameter("email");
			String address = request.getParameter("address");
			int empl = (int) session.getAttribute("accountNumber");
			UserLogDao ed = new UserLogDaoImpl();
			UserLog acc = ed.getUserLogById(empl);
			
			
			
			if(acc == null) {
				response.sendRedirect("/p1-Jorge-Sagrero-Perez/Views/myProfile.html");
			}
			acc.setFirstName(fname);
			acc.setLastName(lname);
			acc.setEmail(email);
			acc.setAddress(address);
			ed.updateUserLog(acc);
			response.sendRedirect("/p1-Jorge-Sagrero-Perez/Views/myProfile.html");
			}
		else {
			response.sendRedirect("login");
		}
		}
		


}

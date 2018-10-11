package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
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
import com.project1.util.ValidateHelper;

/**
 * Servlet implementation class MyResolvedServlet
 */
public class MyResolvedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyResolvedServlet() {
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
			int choice = (int) (session.getAttribute("choice"));
			System.out.println(choice);

			int accountNum = (int) session.getAttribute("accountNumber");
			UserLogDao u = new UserLogDaoImpl();
			UserLog userlogs = u.getUserLogById(accountNum);
			ReimbursementsDao r = new ReimbursementsDaoImpl();
			List<Reimbursements> rr = new ArrayList<>();
			rr = r.getReimbursementByStatusAndId(accountNum, choice);
			

			String userLogString = om.writeValueAsString(rr);
			String userlog2 = om.writeValueAsString(userlogs);
			//userLogString = "{\"userLog\":"+userLogString+"}";

			userLogString = "{\"userLog\":"+userLogString+", \"userLog2\":"+userlog2+"}";
			pw.print(userLogString);
		} 
		else {
			pw.write("{\"userLog\": 0 }");
		}
		
		}	
	


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);//if true was passed in, a session would have been created
		if( session!= null && session.getAttribute("accountNumber")!= null) {
			int choice = Integer.parseInt(request.getParameter("choice"));
			int accountNum = (int) session.getAttribute("accountNumber");
			session.setAttribute("choice", choice);
			System.out.println("choice is = "+ ((int) session.getAttribute("choice")) );
			response.sendRedirect("/p1-Jorge-Sagrero-Perez/Views/myResolved.html");
		} else {
			response.sendRedirect("login");
		}
	}

}

package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project1.dao.UserLogDao;
import com.project1.dao.UserLogDaoImpl;
import com.project1.model.UserLog;

/**
 * Servlet implementation class PrivateReimbursementsServlet
 */
public class PrivateReimbursementsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrivateReimbursementsServlet() {
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
			int accountNum = (int) session.getAttribute("searchEmp");
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
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
			int searchEmp = Integer.parseInt(request.getParameter("userId"));
			session.setAttribute("searchEmp", searchEmp );
			UserLogDao ed = new UserLogDaoImpl();
			UserLog acc = ed.getUserLogById(searchEmp);
			if(acc == null) {
				response.sendRedirect("/p1-Jorge-Sagrero-Perez/Views/employeeList.html");
				}
			else if(acc.getManager()==1){
					response.sendRedirect("/p1-Jorge-Sagrero-Perez/Views/employeeList.html");
				}
			else {
				response.sendRedirect("/p1-Jorge-Sagrero-Perez/Views/privateReimbursements.html");
			}
		}

}

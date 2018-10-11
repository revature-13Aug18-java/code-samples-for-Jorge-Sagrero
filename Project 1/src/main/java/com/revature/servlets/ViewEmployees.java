package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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
 * Servlet implementation class ViewEmployees
 */
public class ViewEmployees extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewEmployees() {
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
			//int accountNum = (Integer) session.getAttribute("accountNumber");
			UserLogDao u = new UserLogDaoImpl();
			List<UserLog> userlogs = u.getUserLog();
			String userLogString = om.writeValueAsString(userlogs);
			userLogString = "{\"userLog\":"+userLogString+"}";
			pw.print(userLogString);
			
		} else {
			pw.write("{\"userLog\": 0 }");
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

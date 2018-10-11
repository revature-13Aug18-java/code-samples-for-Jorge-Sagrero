package com.revature.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project1.dao.UserLogDao;
import com.project1.dao.UserLogDaoImpl;
import com.project1.model.UserLog;
import com.project1.util.ValidateHelper;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("Views/Login.html").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//String user=request.getParameter("username");
		//String pass=request.getParameter("password");
		int accountNumber = ValidateHelper.validateUser(request);
		HttpSession session = request.getSession();
		
		if(accountNumber != 0) {
			session.setAttribute("accountNumber", accountNumber);
			UserLogDao ed = new UserLogDaoImpl();
			UserLog acc = ed.getUserLogById(accountNumber);
			session.setAttribute("fName", acc.getFirstName());
			if(acc.getManager() == 0) {
				response.sendRedirect("profile");
			}
			else if(acc.getManager()==1){
				response.sendRedirect("managerProfile");
			}
		} else {
			response.sendRedirect("login");
		}
	}

}

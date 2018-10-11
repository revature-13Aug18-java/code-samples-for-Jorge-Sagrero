package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project1.dao.UserLogDao;
import com.project1.dao.UserLogDaoImpl;
import com.project1.model.UserLog;

/**
 * Servlet implementation class UserLogServlet
 */
public class UserLogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLogServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("ACCOUNTNUMBER");
		
		//System.out.println("we got a param, value: "+idStr);
		System.out.println(idStr);
		UserLogDao ed = new UserLogDaoImpl();
		ObjectMapper om = new ObjectMapper();
		PrintWriter pw = response.getWriter();
		
		//if we get a parameter we want to display a single employee
		if(idStr != null) {
			int id = Integer.parseInt(idStr);
			UserLog e = ed.getUserLogById(id);
			//if we don't get anything from the database our id will be 0
			if(e==null) {
				pw.print("invalid employee id");
			//if we are returned an employee from the database we want to display it in json format
			} else {
				String userLogString = om.writeValueAsString(e);
				pw.write(userLogString);
			}
		//if we do not get a valid parameter, we want to display all employees
		} else {
			List<UserLog> userlogs = ed.getUserLog();
			String userLogString = om.writeValueAsString(userlogs);
			userLogString = "{\"userLog\":"+userLogString+"}";
			pw.print(userLogString);
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

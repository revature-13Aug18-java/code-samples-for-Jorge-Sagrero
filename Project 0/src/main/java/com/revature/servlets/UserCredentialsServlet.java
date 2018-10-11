package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project0.dao.UserCredentialsDao;
import com.project0.dao.UserCredentialsDaoImpl;
import com.project0.model.UserCredentials;

/**
 * Servlet implementation class UserCredentialsServlet
 */
public class UserCredentialsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserCredentialsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("ACCOUNTNUMBER");
		//System.out.println("we got a param, value: "+idStr);
		System.out.println(idStr);
		UserCredentialsDao ed = new UserCredentialsDaoImpl();
		ObjectMapper om = new ObjectMapper();
		PrintWriter pw = response.getWriter();
		
		//if we get a parameter we want to display a single employee
		if(idStr != null) {
			int id = Integer.parseInt(idStr);
			UserCredentials e = ed.getUserCredentialsById(id);
			//if we don't get anything from the database our id will be 0
			if(e.getAccountNumber().getAccountNumber()==0) {
				pw.print("invalid employee id");
			//if we are returned an employee from the database we want to display it in json format
			} else {
				String employeeString = om.writeValueAsString(e);
				pw.write(employeeString);
			}
		//if we do not get a valid parameter, we want to display all employees
		} else {
			List<UserCredentials> employees = ed.getUserCredentials();
			String employeeString = om.writeValueAsString(employees);
			employeeString = "{\"userCredentials\":"+employeeString+"}";
			pw.print(employeeString);
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

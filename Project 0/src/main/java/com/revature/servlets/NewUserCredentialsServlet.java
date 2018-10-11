package com.revature.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project0.model.UserCredentials;

/**
 * Servlet implementation class NewUserCredentialsServlet
 */
public class NewUserCredentialsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewUserCredentialsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("Views/CreateUserCredentials.html").forward(request, response);;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		int locationId = Integer.parseInt(request.getParameter("location"));
		int departmentId = Integer.parseInt(request.getParameter("department"));
	
		UserCredentials e = new UserCredentials();
		e.setName(name);
		Location l = new Location();
		l.setId(locationId);
		e.setLocation(l);
		Department d = new Department();
		d.setId(departmentId);
		e.setDepartment(d);
		
		System.out.println(e);
		
		EmployeeDao ed = new EmployeeDaoImpl();
		int numCreated = ed.createEmployee(e);
		System.out.println(numCreated);
		
		if(numCreated==1) {
			response.sendRedirect("success");
		} else {
			response.sendRedirect("error");
		}
	}

}
package com.revature.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project1.dao.ReimbursementsDao;
import com.project1.dao.ReimbursementsDaoImpl;
import com.project1.dao.UserLogDao;
import com.project1.dao.UserLogDaoImpl;
import com.project1.model.Reimbursements;
import com.project1.model.UserLog;
import com.project1.util.ValidateHelper;

/**
 * Servlet implementation class EditReimbursementsServlet
 */
public class EditReimbursementsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditReimbursementsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
//Integer.parseInt(request.getParameter("receiptId")
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);//if true was passed in, a session would have been created
		if( session!= null && session.getAttribute("accountNumber")!= null) {
			ReimbursementsDao d = new ReimbursementsDaoImpl();
			int receiptId = Integer.parseInt(request.getParameter("receiptId"));
			int receipt = Integer.parseInt(request.getParameter("receipt"));
			int manager = (int) session.getAttribute("accountNumber");
			Reimbursements dd = d.getReimbursementByReceipt(receiptId);
			if(dd == null) {
				response.sendRedirect("/p1-Jorge-Sagrero-Perez/Views/privateReimbursements.html");
			}
			
			dd.setStatus(receipt);
			dd.setManagerId(manager);
			d.updateReimbursementsByReceipt(dd);
			response.sendRedirect("/p1-Jorge-Sagrero-Perez/Views/privateReimbursements.html");
			}
		else {
			response.sendRedirect("login");
		}
		}
}

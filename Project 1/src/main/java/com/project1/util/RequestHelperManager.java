package com.project1.util;

import javax.servlet.http.HttpServletRequest;

public class RequestHelperManager {

	public static String process(HttpServletRequest request) {
		switch(request.getParameter("destination")) {
		case "employeeList":
			return "employeeList";
		case "pendingReimbursements":
			return "pendingReimbursements";
		case "completedReimbursements":
			return "completedReimbursements";
		case "privateReimbursements":
			return "privateReimbursements";
		case "editReimbursements":
			return "editReimbursements";
		default:
			return "managerProfile";
		}
	}
}


package com.project0.util;
//replaces the logic withing the master servlet, use this method to get the result for the servlets within our servlet
import javax.servlet.http.HttpServletRequest;

public class RequestHelper {
	
	public static String process(HttpServletRequest request) {
		switch(request.getParameter("destination")) {
		case "directory":
			return "directory";
		case "new":
			return "new";
		default:
			return "home";
		}
	}

}

package com.project1.util;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.project1.dao.UserLogDao;
import com.project1.dao.UserLogDaoImpl;
import com.project1.model.UserLog;



public class ValidateHelper {

	public static int validateUser(HttpServletRequest request) {
		String usrName = request.getParameter("username");
		String psw = request.getParameter("password");
		Connection connection;
		
				try {
					connection = ConnectionUtil.getConnection();
					CallableStatement proc = connection.prepareCall("{ ? = call VALIDATE_USER(?,?) }");
					proc.registerOutParameter(1, Types.VARCHAR);
					proc.setString(2, usrName);
					proc.setString(3, psw);
					proc.execute();
					String returnValue = proc.getString(1);
					if(returnValue.equals("TRUE")) {
						UserLogDao edi = new UserLogDaoImpl();
						List<UserLog> allUserLog = edi.getUserLog();
						for(UserLog e: allUserLog) {
							if(usrName.equals(e.getEmail())) {
								return e.getAccountNumber().get(0).getAccountNumber();
							}
						}
						
					}
					return 0;
				} catch (IOException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				return 0;
				
			}
}


package com.project1.dao;

import java.sql.Connection;
import java.util.List;

import com.project1.model.UserLog;


public interface UserLogDao {

	public List<UserLog> getUserLog();
	public UserLog getUserLogById(int id);
	public UserLog getUserLogById(int id, Connection con);
	public int createUserLog(UserLog userLog);
	public int updateUserLog(UserLog userLog);
	public int deleteUserLogById(int id);
}

package com.project0.dao;

import java.sql.Connection;
import java.util.List;

import com.project0.model.UserCredentials;

public interface UserCredentialsDao {

	
	public List<UserCredentials> getUserCredentials();
	public UserCredentials getUserCredentialsById(int id);
	public UserCredentials getUserCredentialsById(int id, Connection con);
	public int createUserCredentials(UserCredentials userCredentials);
	public int updateUserCredentials(UserCredentials userCredentials);
	public int deleteUserCredentialsById(int id);
}

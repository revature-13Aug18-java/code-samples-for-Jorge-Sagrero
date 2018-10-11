package com.project0.dao;

import java.sql.Connection;
import java.util.List;

import com.project0.model.UserAccount;

public interface UserAccountDao {
	public List<UserAccount> getUserAccounts();
	public UserAccount getUserAccountById(int id);
	public UserAccount getUserAccountById(int id, Connection con);
	public int createUserAccount(UserAccount user);
	public int updateUserAccount(UserAccount user);
	public int deleteUserAccountById(int id);
	public void increaseBalance(int id, double increaseAmount);

}

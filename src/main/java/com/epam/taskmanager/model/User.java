package com.epam.taskmanager.model;

import java.util.ArrayList;
import java.util.List;

public class User {

	private long userID;
	private String userName;
	private String password;
	private static List<User> userList = new ArrayList<User>();

	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void addUser(User user) {
		userList.add(user);
	}

	public List<User> getUserList() {
		return userList;
	}
}

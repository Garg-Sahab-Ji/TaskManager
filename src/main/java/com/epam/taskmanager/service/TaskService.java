package com.epam.taskmanager.service;

import com.epam.taskmanager.model.User;

public interface TaskService {
	public void newTask(User user);

	public void readTask(User user);

	public void deleteTask(User user);
}

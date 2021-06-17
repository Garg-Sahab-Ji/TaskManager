/*
* Class name: TaskService
*
* Version info: jdk 1.8
*
* Copyright notice:
* 
* Author info: Arpit Garg
*
* Creation date: 15/Jun/2021
*
* Last updated By: Arpit Garg
*
* Last updated Date: 15/Jun/2021
*
* Description: Used to perform operation on Tasks
*/
package com.epam.taskmanager.service;

import com.epam.taskmanager.model.Notes;
import com.epam.taskmanager.model.Task;
import com.epam.taskmanager.model.User;

public interface TaskService {
	/**
	 * Create a new Task
	 * 
	 * @param user
	 */
	public void newTask(Task task);

	/**
	 * Display all task
	 * 
	 * @param user
	 */
	public void readTask(User user);

	/**
	 * Delete a task
	 * 
	 * @param user
	 */
	public void deleteTask(User user, long taskIdToDelete);

	/**
	 * Add notes to the Task
	 * 
	 * @param user
	 */
	public void addNotes(User user, long updateTaskId, Notes note);

	/**
	 * Update a Task
	 * 
	 * @param user
	 */
	public void updateTask(User user, long updateTaskId, int choice);
}

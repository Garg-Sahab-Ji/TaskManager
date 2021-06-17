/*
* Class name: Task
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
* Description: Model Class of Tasks
*/
package com.epam.taskmanager.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Task {

	private long taskID;
	private LocalDateTime taskStartTime;
	private LocalDateTime taskEndTime;
	private String taskTitle;
	private List<Notes> notesList = new ArrayList<Notes>();
	private long userId;
	private static List<Task> taskList = new ArrayList<Task>();

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getTaskID() {
		return taskID;
	}

	public void setTaskID(long taskID) {
		this.taskID = taskID;
	}

	public LocalDateTime getTaskStartTime() {
		return taskStartTime;
	}

	public void setTaskStartTime(LocalDateTime taskStartTime) {
		this.taskStartTime = taskStartTime;
	}

	public LocalDateTime getTaskEndTime() {
		return taskEndTime;
	}

	public void setTaskEndTime(LocalDateTime taskEndTime) {
		this.taskEndTime = taskEndTime;
	}

	public String getTaskTitle() {
		return taskTitle;
	}

	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}

	public void addTask(Task task) {
		taskList.add(task);
	}

	public List<Task> getTaskList() {
		return taskList;
	}

	public void addNotes(Notes notes) {
		this.notesList.add(notes);
	}

	public List<Notes> getNotesList() {
		return notesList;
	}
}

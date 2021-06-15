package com.epam.taskmanager.model;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class Task {

	private long taskID;
	private Date taskDate;
	private Time taskStartTime;
	private Time taskEndTime;
	private String taskTitle;
	private String taskDescription;
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

	public Date getTaskDate() {
		return taskDate;
	}

	public void setTaskDate(Date taskDate) {
		this.taskDate = taskDate;
	}

	public Time getTaskStartTime() {
		return taskStartTime;
	}

	public void setTaskStartTime(Time taskStartTime) {
		this.taskStartTime = taskStartTime;
	}

	public Time getTaskEndTime() {
		return taskEndTime;
	}

	public void setTaskEndTime(Time taskEndTime) {
		this.taskEndTime = taskEndTime;
	}

	public String getTaskTitle() {
		return taskTitle;
	}

	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}

	public String getTaskDescription() {
		return taskDescription;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	public void addTask(Task task) {
		taskList.add(task);
	}

	public List<Task> getTaskList() {
		return taskList;
	}
}

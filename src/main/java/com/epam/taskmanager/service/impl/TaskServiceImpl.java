package com.epam.taskmanager.service.impl;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.epam.taskmanager.exception.TaskException;
import com.epam.taskmanager.model.Task;
import com.epam.taskmanager.model.User;
import com.epam.taskmanager.service.TaskService;
import com.epam.taskmanager.utils.ValidationUtil;

public class TaskServiceImpl implements TaskService {
	private long taskId;
	private Date taskDate;
	private Time taskStartTime;
	private Time taskEndTime;
	private String taskTitle;
	private String taskDescription;
	private static Scanner scan = new Scanner(System.in);

	@Override
	public void newTask(User user) {
		taskId();
		enterTaskDate();
		enterStartingTaskTime();
		enterEndingingTaskTime();
		enterTaskTitle();
		enterTaskDescription();
		Task task = new Task();
		task.setTaskID(this.taskId);
		task.setTaskDate(this.taskDate);
		task.setTaskStartTime(this.taskStartTime);
		task.setTaskEndTime(this.taskEndTime);
		task.setTaskTitle(this.taskTitle);
		task.setTaskDescription(this.taskDescription);
		task.setUserId(user.getUserID());
		new Task().addTask(task);
	}

	private void taskId() {
		this.taskId = (long) (Math.random() * 1000);
	}

	private void enterTaskDate() {
		System.out.println("Enter Task Date: \nFormate: YYYY-MM-DD");
		boolean flag = true;
		while (flag) {
			String taskDate = scan.nextLine();
			try {
				new ValidationUtil().dateValidation(taskDate);
				this.taskDate = Date.valueOf(taskDate);
				Date todayDate = new java.sql.Date(System.currentTimeMillis());
				if (this.taskDate.before(todayDate)) {
					throw new TaskException("Enter Future Date");
				}
				System.out.println(Date.valueOf(taskDate));
				flag = false;
			} catch (TaskException e) {
				System.out.println("Formate: YYYY-MM-DD");
				flag = true;
			}
		}
	}

	private void enterStartingTaskTime() {
		System.out.println("Enter Task Time: \nFormate: HH:MM:SS");
		boolean flag = true;
		while (flag) {
			String taskStartingTime = scan.nextLine();
			try {
				new ValidationUtil().timeValidation(taskStartingTime);
				this.taskStartTime = Time.valueOf(taskStartingTime);
				System.out.println(Time.valueOf(taskStartingTime));
				flag = false;
			} catch (TaskException e) {
				System.out.println("Formate: HH:MM:SS");
				flag = true;
			}
		}
	}

	private void enterEndingingTaskTime() {
		System.out.println("Enter Task Time: \nFormate: HH:MM:SS");
		boolean flag = true;
		while (flag) {
			String taskEndingingTime = scan.nextLine();
			try {
				new ValidationUtil().timeValidation(taskEndingingTime);
				this.taskEndTime = Time.valueOf(taskEndingingTime);
				if (this.taskEndTime.before(this.taskStartTime)) {
					throw new TaskException("Ending time must be after starting time");
				}
				System.out.println(Time.valueOf(taskEndingingTime));
				flag = false;
			} catch (TaskException e) {
				System.out.println("Formate: HH:MM:SS");
				flag = true;
			}
		}
	}

	private void enterTaskTitle() {
		System.out.println("Task Title: ");
		String taskTitle = scan.nextLine();
		this.taskTitle = taskTitle;
	}

	private void enterTaskDescription() {
		System.out.println("Task Description: ");
		String taskDescription = scan.nextLine();
		this.taskDescription = taskDescription;
	}

	@Override
	public void readTask(User user) {
		List<Task> taskList = new Task().getTaskList();
		List<Task> userTasks = taskList.stream().filter(task -> user.getUserID() == task.getUserId())
				.collect(Collectors.toList());
		System.out.println(
				"----------------------------------------------------------------------------------------------------------");
		System.out.printf("%10s | %10s | %10s | %10s | %15s | %15s", "Task ID", "Dete", "Start", "End", "Title",
				"Description");
		System.out.println();
		if (userTasks.isEmpty()) {
			System.out.println("No Task Available");
			System.out.println(
					"----------------------------------------------------------------------------------------------------------");
		} else {
			userTasks.forEach(task -> printTasks(task));
		}
	}

	private void printTasks(Task task) {
		System.out.printf("%10s | %10s | %10s | %10s | %15s | %15s", task.getTaskID(), task.getTaskDate(),
				task.getTaskStartTime(), task.getTaskEndTime(), task.getTaskTitle(), task.getTaskDescription());
		System.out.println();
		System.out.println(
				"----------------------------------------------------------------------------------------------------------");
	}

	@Override
	public void deleteTask(User user) {
		
	}
}

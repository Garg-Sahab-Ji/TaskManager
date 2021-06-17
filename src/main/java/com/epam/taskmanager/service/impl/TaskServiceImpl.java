/*
* Class name: TaskServiceImpl
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
package com.epam.taskmanager.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import com.epam.taskmanager.TaskOperation;
import com.epam.taskmanager.constants.Constants;
import com.epam.taskmanager.model.Notes;
import com.epam.taskmanager.model.Task;
import com.epam.taskmanager.model.User;
import com.epam.taskmanager.service.TaskService;

public class TaskServiceImpl implements TaskService {

	private LocalDateTime taskStartTime;
	private LocalDateTime taskEndTime;
	private String taskTitle;
	private Notes notes;

	/**
	 * Create a new Task
	 * 
	 * @param user
	 */
	@Override
	public void newTask(final Task task) {
		new Task().addTask(task);
		System.out.println(Constants.TASK_ADDED);
	}

	/**
	 * used for read list of task
	 * 
	 * @param user
	 */
	@Override
	public void readTask(final User user) {
		final List<Task> taskList = new Task().getTaskList();
		final List<Task> userTasks = taskList.stream().filter(task -> user.getUserID() == task.getUserId())
				.collect(Collectors.toList());
		System.out.println(
				"----------------------------------------------------------------------------------------------------------");
		System.out.printf("%10s | %20s  | %20s | %18s | %20s", "Task ID", "Start At", "End At", "Title", "Description");
		System.out.println();
		System.out.println(
				"----------------------------------------------------------------------------------------------------------");
		if (userTasks.isEmpty()) {
			System.out.printf("%50s", Constants.NO_TASK_AVAILABLE);
			System.out.println();
			System.out.println(
					"----------------------------------------------------------------------------------------------------------");
		} else {
			userTasks.forEach(task -> printTasks(task));
		}
	}

	/**
	 * print the task
	 * 
	 * @param task
	 */
	private void printTasks(final Task task) {
		final DateTimeFormatter dateTimeFormate = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		String taskNotes = printNotes(task.getNotesList());
		System.out.printf("%10s | %20s  | %20s | %18s | %20s", task.getTaskID(),
				task.getTaskStartTime().format(dateTimeFormate), task.getTaskEndTime().format(dateTimeFormate),
				task.getTaskTitle(), taskNotes);
		System.out.println();
		System.out.println(
				"----------------------------------------------------------------------------------------------------------");
	}

	private String printNotes(List<Notes> notes) {
		String notesDescription = "";
		for (Notes note : notes) {
			notesDescription += note.getNotesDescription() + "\n";
		}
		return notesDescription;
	}

	/**
	 * used to delete a task
	 * 
	 * @param user
	 */
	@Override
	public void deleteTask(final User user, final long taskIdToDelete) {
		final List<Task> t = new Task().getTaskList();
		t.removeIf(task -> task.getTaskID() == taskIdToDelete);
		System.out.println(Constants.TASK_REMOVED);
	}

	/**
	 * used to add a notes
	 * 
	 * @param user
	 */
	@Override
	public void addNotes(final User user, final long updateTaskId, final Notes notes) {
		new Task().getTaskList().stream().filter(task -> task.getTaskID() == updateTaskId).findFirst()
				.ifPresent(updateTask -> updateTask.addNotes(notes));
	}

	/**
	 * used to update a task
	 * 
	 * @param user
	 */
	@Override
	public void updateTask(final User user, final long updateTaskId, final int choice) {
		switch (choice) {
		case 1:
			this.taskStartTime = new TaskOperation().enterStartingTaskTime(updateTaskId);
			new Task().getTaskList().stream().filter(task -> task.getTaskID() == updateTaskId).findFirst()
					.ifPresent(updateTask -> updateTask.setTaskStartTime(this.taskStartTime));
			System.out.println(Constants.TASK_START_TIME_UPDATED);
			break;
		case 2:
			this.taskEndTime = new TaskOperation().enterEndingingTaskTime(
					new Task().getTaskList().stream().filter(task -> task.getTaskID() == updateTaskId)
							.collect(Collectors.toList()).get(0).getTaskStartTime(),
					updateTaskId);
			new Task().getTaskList().stream().filter(task -> task.getTaskID() == updateTaskId).findFirst()
					.ifPresent(updateTask -> updateTask.setTaskEndTime(this.taskEndTime));
			System.out.println(Constants.TASK_END_TIME_UPDATED);

			break;
		case 3:
			this.taskTitle = new TaskOperation().enterTaskTitle();
			new Task().getTaskList().stream().filter(task -> task.getTaskID() == updateTaskId).findFirst()
					.ifPresent(updateTask -> updateTask.setTaskTitle(this.taskTitle));
			System.out.println(Constants.TASK_TITLE_UPDATED);
			break;
		case 4:
			this.notes = new TaskOperation().enterTaskDescription();
			new Task().getTaskList().stream().filter(task -> task.getTaskID() == updateTaskId).findFirst()
					.ifPresent(updateTask -> updateTask.addNotes(notes));
			System.out.println(Constants.TASK_DESCRIPTION_UPDATED);
			break;
		case 5:
			break;
		default:
			System.out.println(Constants.WRONG_CHOICE);
			break;
		}
	}
}

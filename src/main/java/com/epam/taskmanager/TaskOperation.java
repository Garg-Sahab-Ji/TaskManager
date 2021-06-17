
/*
* Class name: TaskOperation
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
package com.epam.taskmanager;

import java.time.LocalDateTime;
import java.util.Scanner;

import com.epam.taskmanager.constants.Constants;
import com.epam.taskmanager.exception.TaskException;
import com.epam.taskmanager.model.Notes;
import com.epam.taskmanager.model.Task;
import com.epam.taskmanager.model.User;
import com.epam.taskmanager.service.impl.TaskServiceImpl;
import com.epam.taskmanager.utils.ValidationUtil;

public class TaskOperation {

	private long taskId;
	private LocalDateTime taskStartTime;
	private LocalDateTime taskEndTime;
	private String taskTitle;
	private Notes notes;
	private static Scanner scan = new Scanner(System.in);

	/**
	 * create a new Task
	 * 
	 * @param user
	 */
	public void newTask(final User user) {
		this.taskId = generateTaskId();
		this.taskStartTime = enterStartingTaskTime(this.taskId);
		this.taskEndTime = enterEndingingTaskTime(this.taskStartTime, this.taskId);
		this.taskTitle = enterTaskTitle();
		this.notes = enterTaskDescription();
		final Task task = new Task();
		task.setTaskID(this.taskId);
		task.setTaskStartTime(this.taskStartTime);
		task.setTaskEndTime(this.taskEndTime);
		task.setTaskTitle(this.taskTitle);
		task.addNotes(notes);
		task.setUserId(user.getUserID());
		new TaskServiceImpl().newTask(task);
	}

	/**
	 * Generate a unique Task Id
	 */
	private long generateTaskId() {
		return (long) (Math.random() * 1000);
	}

	private long generateNotesID() {
		return (long) (Math.random() * 10000);
	}

	/**
	 * used for taking starting time of task
	 */
	public LocalDateTime enterStartingTaskTime(final long taskId) {
		System.out.println(Constants.ENTER_TASK_STARTING_DATE_AND_TIME_FORMATE_YYYY_MM_DD_HH_MM_SS);
		boolean flag = true;
		while (flag) {
			final String taskStartingDate = scan.nextLine();
			try {
				final LocalDateTime dateTime = new ValidationUtil().dateTimeValidation(taskStartingDate);
				this.taskStartTime = dateTime;
				final LocalDateTime currentTime = LocalDateTime.now();
				if (this.taskStartTime.isBefore(currentTime)) {
					throw new TaskException(Constants.INVALID_ENTER_FUTURE_DATE);
				}
				if (timeConflict(dateTime, taskId)) {
					throw new TaskException(
							Constants.THERE_IS_ALREADY_A_TASK_AT_THIS_TIME_ENTER_STARTING_DATE_AND_TIME_AGAIN);
				}
				flag = false;
			} catch (final TaskException e) {
				System.out.println(Constants.FORMATE_YYYY_MM_DD_HH_MM_SS);
				flag = true;
			}
		}
		return this.taskStartTime;
	}

	/**
	 * used for taking end time of task
	 * 
	 * @param taskStartTime
	 */
	public LocalDateTime enterEndingingTaskTime(final LocalDateTime taskStartTime, final long taskId) {
		System.out.println(Constants.ENTER_TASK_ENDING_DATE_AND_TIME_FORMATE_YYYY_MM_DD_HH_MM_SS);
		boolean flag = true;
		while (flag) {
			final String taskEndingingTime = scan.nextLine();
			try {
				final LocalDateTime dateTime = new ValidationUtil().dateTimeValidation(taskEndingingTime);
				this.taskEndTime = dateTime;
				if (this.taskEndTime.isBefore(taskStartTime)) {
					throw new TaskException(Constants.ENDING_TIME_MUST_BE_AFTER_STARTING_TIME);
				}
				if (timeConflict(dateTime, taskId)) {
					throw new TaskException(
							Constants.THERE_IS_ALREADY_A_TASK_AT_THIS_TIME_ENTER_ENDING_DATE_AND_TIME_AGAIN);
				}
				flag = false;
			} catch (final TaskException e) {
				System.out.println(Constants.FORMATE_YYYY_MM_DD_HH_MM_SS);
				flag = true;
			}
		}
		return this.taskEndTime;
	}

	private boolean timeConflict(final LocalDateTime dateTime, final long taskId) {
		return new Task().getTaskList().stream().anyMatch(
				task -> ((task.getTaskStartTime().isBefore(dateTime) && task.getTaskEndTime().isAfter(dateTime))
						|| (task.getTaskStartTime().isEqual(dateTime) || task.getTaskEndTime().isEqual(dateTime)))
						&& task.getTaskID() != taskId);
	}

	/**
	 * used for taking task title
	 */
	public String enterTaskTitle() {
		System.out.println(Constants.TASK_TITLE);
		final String taskTitle = scan.nextLine();
		this.taskTitle = taskTitle;
		return this.taskTitle;
	}

	/**
	 * used for taking task notes/description
	 */
	public Notes enterTaskDescription() {
		long notesId = this.generateNotesID();
		Notes note = new Notes();
		note.setNotesId(notesId);
		System.out.println(Constants.TASK_DESCRIPTION);
		String taskDescription = "";
		taskDescription += scan.nextLine();
		note.setNotesDescription(taskDescription);
		return note;
	}

	/**
	 * give all task that are available
	 * 
	 * @param user
	 */
	public void readTask(final User user) {
		new TaskServiceImpl().readTask(user);
	}

	private boolean isTaskPresent(long taskId) {
		return new Task().getTaskList().stream().anyMatch(task -> task.getTaskID() == taskId);
	}

	/**
	 * delete a task
	 * 
	 * @param user
	 */
	public void deleteTask(final User user) {
		System.out.println(Constants.THE_LIST_OF_TASKS_ARE);
		readTask(user);
		System.out.println(Constants.ENTER_TASK_ID_TO_DELETE);
		final String taskId = scan.nextLine();
		final long taskIdToDelete = new ValidationUtil().taskIDValidation(taskId);
		if (!isTaskPresent(taskIdToDelete)) {
			throw new TaskException(Constants.NO_TASK_AVAILABLE_WITH_THIS_ID);
		}
		new TaskServiceImpl().deleteTask(user, taskIdToDelete);
	}

	public void updateTask(final User user) {
		System.out.println(Constants.THE_LIST_OF_TASKS_ARE);
		readTask(user);
		if (new Task().getTaskList().isEmpty()) {
			return;
		}
		System.out.println(Constants.ENTER_TASK_ID_TO_UPDATE);
		final String taskId = scan.nextLine();
		final long updateTaskId = new ValidationUtil().taskIDValidation(taskId);
		if (!isTaskPresent(updateTaskId)) {
			throw new TaskException(Constants.NO_TASK_AVAILABLE_WITH_THIS_ID);
		}
		System.out.println(Constants.SELECT_THE_FIELD_WHICH_YOU_WANT_TO_UPDATE);
		System.out.println(Constants._1_START_TIME);
		System.out.println(Constants._2_END_TIME);
		System.out.println(Constants._3_TASK_TITLE);
		System.out.println(Constants._4_TASK_DESCRIPTION);
		System.out.println(Constants._5_BACK);
		final int choice = Integer.parseInt(scan.nextLine());
		new TaskServiceImpl().updateTask(user, updateTaskId, choice);
	}

	/**
	 * Used to add notes
	 * 
	 * @param user
	 */
	public void addNotes(final User user) {
		System.out.println(Constants.THE_LIST_OF_TASKS_ARE);
		readTask(user);
		if (new Task().getTaskList().isEmpty()) {
			return;
		}
		System.out.println(Constants.ENTER_TASK_ID_TO_ADD_NOTES);
		final String taskId = scan.nextLine();
		final long updateTaskId = new ValidationUtil().taskIDValidation(taskId);
		if (!isTaskPresent(updateTaskId)) {
			throw new TaskException(Constants.NO_TASK_AVAILABLE_WITH_THIS_ID);
		}
		System.out.println(Constants.ENTER_NOTES);
		long notesId = generateNotesID();
		final String notesToUpdate = scan.nextLine();
		Notes note = new Notes();
		note.setNotesId(notesId);
		note.setNotesDescription(notesToUpdate);
		new TaskServiceImpl().addNotes(user, updateTaskId, note);
	}
}

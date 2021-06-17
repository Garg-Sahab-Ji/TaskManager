/*
* Class name: TaskSchedule
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
* Description: Used to Schedule a Task 
*/
package com.epam.taskmanager;

import java.util.Scanner;

import com.epam.taskmanager.constants.Constants;
import com.epam.taskmanager.exception.TaskException;
import com.epam.taskmanager.model.User;
import com.epam.taskmanager.utils.ValidationUtil;

public class TaskSchedule {

	private final Scanner scan = new Scanner(System.in);

	/**
	 * used for operations on Task
	 * 
	 * @param user
	 */
	public void taskOperation(final User user) {
		boolean flag = true;
		do {
			System.out.println(Constants.SELECT_TASK_OPERATION);
			System.out.println(Constants._1_NEW_TASK);
			System.out.println(Constants._2_READ_TASK);
			System.out.println(Constants._3_DELETE_TASK);
			System.out.println(Constants._4_UPDATE_TASK);
			System.out.println(Constants._5_ADD_NOTES);
			System.out.println(Constants._6_SIGN_OUT);
			final String choice = scan.next();
			try {
				final int choiceOperation = new ValidationUtil().choiceValidation(choice);
				switch (choiceOperation) {
				case 1:
					new TaskOperation().newTask(user);
					break;
				case 2:
					new TaskOperation().readTask(user);
					break;
				case 3:
					new TaskOperation().deleteTask(user);
					break;
				case 4:
					new TaskOperation().updateTask(user);
					break;
				case 5:
					new TaskOperation().addNotes(user);
					break;
				case 6:
					flag = !flag;
					break;
				default:
					System.out.println(Constants.WRONG_CHOICE);
					break;
				}
			} catch (final TaskException e) {
				System.out.println(Constants.RE_ENTER_CHOICE);
			}
		} while (flag);
	}

}

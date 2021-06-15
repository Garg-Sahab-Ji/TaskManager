package com.epam.taskmanager;

import java.util.Scanner;

import com.epam.taskmanager.constants.Constants;
import com.epam.taskmanager.exception.TaskException;
import com.epam.taskmanager.model.User;
import com.epam.taskmanager.service.impl.TaskServiceImpl;
import com.epam.taskmanager.utils.ValidationUtil;

public class TaskSchedule {

	private Scanner scan = new Scanner(System.in);

	public void taskOperation(User user) {
		boolean flag = true;
		do {
			System.out.println("Select Task Operation");
			System.out.println("1. New Task");
			System.out.println("2. Read Task");
			System.out.println("3. Delete Task");
			System.out.println("4. Update Task");
			System.out.println("5. Logout");
			String choice = scan.next();
			try {
				int choiceOperation = new ValidationUtil().choiceValidation(choice);
				switch (choiceOperation) {
				case 1:
					new TaskServiceImpl().newTask(user);
					break;
				case 2:
					new TaskServiceImpl().readTask(user);
					break;
				case 3:
					break;
				case 4:
					break;
				case 5:
					flag = !flag;
					break;
				default:
					System.out.println(Constants.WRONG_CHOICE);
					break;
				}
			} catch (TaskException e) {
				System.out.println("Re-enter choice..");
			}
		} while (flag);
	}

}

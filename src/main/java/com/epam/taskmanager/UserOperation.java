package com.epam.taskmanager;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.epam.taskmanager.constants.Constants;
import com.epam.taskmanager.exception.TaskException;
import com.epam.taskmanager.model.User;
import com.epam.taskmanager.utils.ValidationUtil;

public class UserOperation {

	private static Scanner scan = new Scanner(System.in);

	public void userSelection() {
		System.out.println(Constants.WELCOME);
		boolean exitChoice = false;
		do {
			System.out.println(Constants._1_LOGIN);
			System.out.println(Constants._2_SIGNUP);
			System.out.println(Constants._3_EXIT);
			System.out.println(Constants.ENTER_YOUR_CHOICE);
			String choice = scan.next();
			try {
				int userChoice = new ValidationUtil().choiceValidation(choice);
				switch (userChoice) {
				case 1:
					userLogin();
					break;
				case 2:
					userRegistration();
					break;
				case 3:
					System.out.println(Constants.PRESS_Y_FOR_EXIT);
					char ch = scan.next().charAt(0);
					if (ch == 'y') {
						exitChoice = !exitChoice;
					}
					break;
				default:
					System.out.println(Constants.WRONG_CHOICE);
					break;
				}
			} catch (TaskException e) {
				System.out.println("Reneter..");
			}

		} while (!exitChoice);
		System.out.println(Constants.THANK_YOU);
	}

	private static void userLogin() {
		List<User> userList = new User().getUserList();
		System.out.println(Constants.ENTER_YOUR_USER_NAME);
		String userName = scan.next();
		System.out.println(Constants.ENTER_YOUR_PASSWORD);
		String password = scan.next();
		List<User> loginUser = userList.stream()
				.filter(user -> user.getUserName().equals(userName) && user.getPassword().equals(password))
				.collect(Collectors.toList());
		if (!loginUser.isEmpty()) {
			System.out.println("welcome "+loginUser.get(0).getUserName());
			new TaskSchedule().taskOperation(loginUser.get(0));
		}
	}

	private static void userRegistration() {
		long userId = (long) (Math.random() * 1000);
		System.out.println(Constants.ENTER_YOUR_USER_NAME);
		String userName = scan.next();
		System.out.println(Constants.ENTER_YOUR_PASSWORD);
		String password = scan.next();
		User user = new User();
		user.setUserID(userId);
		user.setUserName(userName);
		user.setPassword(password);
		user.addUser(user);
		System.out.println(Constants.REGISTER_SUCCESSFUL);
		return;
	}
}

/*
* Class name: UserOperation
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
* Description: Used to User signup and login
*/
package com.epam.taskmanager;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.taskmanager.constants.Constants;
import com.epam.taskmanager.exception.TaskException;
import com.epam.taskmanager.model.User;
import com.epam.taskmanager.utils.ValidationUtil;

public class UserOperation {

	private static Scanner scan = new Scanner(System.in);
	private static final Logger LOGGER = LogManager.getLogger(UserOperation.class);

	/**
	 * used for user operation
	 */
	public void userSelection() {
		LOGGER.info(Constants.WELCOME);
		boolean exitChoice = false;
		do {
			LOGGER.info(Constants._1_LOGIN);
			LOGGER.info(Constants._2_SIGNUP);
			LOGGER.info(Constants._3_EXIT);
			LOGGER.info(Constants.ENTER_YOUR_CHOICE);
			final String choice = scan.next();
			try {
				final int userChoice = new ValidationUtil().choiceValidation(choice);
				switch (userChoice) {
				case 1:
					userLogin();
					break;
				case 2:
					userRegistration();
					break;
				case 3:
					LOGGER.info(Constants.PRESS_Y_FOR_EXIT);
					final char ch = scan.next().charAt(0);
					if (ch == 'y') {
						exitChoice = !exitChoice;
					}
					break;
				default:
					LOGGER.warn(Constants.WRONG_CHOICE);
					break;
				}
			} catch (final TaskException e) {
				LOGGER.info("Reneter..");
			}

		} while (!exitChoice);
		LOGGER.info(Constants.THANK_YOU);
	}

	/**
	 * user login method
	 */
	private static void userLogin() {
		final List<User> userList = new User().getUserList();
		LOGGER.info(Constants.ENTER_YOUR_USER_NAME);
		final String userName = scan.next();
		LOGGER.info(Constants.ENTER_YOUR_PASSWORD);
		final String password = scan.next();
		final List<User> loginUser = userList.stream()
				.filter(user -> user.getUserName().equals(userName) && user.getPassword().equals(password))
				.collect(Collectors.toList());
		if (!loginUser.isEmpty()) {
			LOGGER.info("welcome " + loginUser.get(0).getUserName());
			new TaskSchedule().taskOperation(loginUser.get(0));
		}
	}

	/**
	 * User signup
	 */
	private static void userRegistration() {
		final long userId = (long) (Math.random() * 1000);
		LOGGER.info(Constants.ENTER_YOUR_USER_NAME);
		final String userName = scan.next();
		LOGGER.info(Constants.ENTER_YOUR_PASSWORD);
		final String password = scan.next();
		final User user = new User();
		user.setUserID(userId);
		user.setUserName(userName);
		user.setPassword(password);
		user.addUser(user);
		LOGGER.info(Constants.REGISTER_SUCCESSFUL);
		return;
	}
}

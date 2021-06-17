/*
* Class name: ValidationUtil
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
* Description: Used to perform validation on inputs
*/
package com.epam.taskmanager.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.epam.taskmanager.constants.Constants;
import com.epam.taskmanager.exception.TaskException;

public class ValidationUtil {

	/**
	 * used to validate the choice parameter
	 * 
	 * @param choice
	 * @return
	 */
	public int choiceValidation(String choice) {
		try {
			return Integer.parseInt(choice);
		} catch (Exception e) {
			throw new TaskException(Constants.INVALID_VALUE_MUST_BE_A_NUMBER);
		}
	}

	/**
	 * used to validate the task id
	 * 
	 * @param taskId
	 * @return
	 */
	public long taskIDValidation(String taskId) {
		try {
			return Long.parseLong(taskId);
		} catch (Exception e) {
			throw new TaskException(Constants.INVALID_VALUE_MUST_BE_A_NUMBER);
		}
	}

	/**
	 * used to validate the date and time
	 * 
	 * @param dateTtime
	 * @return
	 */
	public LocalDateTime dateTimeValidation(String dateTtime) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime dateTime = LocalDateTime.parse(dateTtime, formatter);
			return dateTime;
		} catch (Exception e) {
			throw new TaskException(Constants.INVALID_RE_ENTER_DATE);
		}
	}
}

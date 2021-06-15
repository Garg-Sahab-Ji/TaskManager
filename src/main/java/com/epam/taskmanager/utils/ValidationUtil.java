package com.epam.taskmanager.utils;

import java.sql.Date;
import java.sql.Time;

import com.epam.taskmanager.exception.TaskException;

public class ValidationUtil {
	public int choiceValidation(String choice) {
		try {
			return Integer.parseInt(choice);
		} catch (Exception e) {
			throw new TaskException("Invalid! value must be a number");
		}
	}

	public void dateValidation(String date) {
		try {
			Date.valueOf(date);
		} catch (Exception e) {
			throw new TaskException("Invalid! Re-enter task date");
		}
	}
	public void timeValidation(String time) {
		try {
			Time.valueOf(time);
		} catch (Exception e) {
			throw new TaskException("Invalid! Re-enter Time");
		}
	}
}

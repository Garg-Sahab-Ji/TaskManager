package com.epam.taskmanager.exception;

public class TaskException extends RuntimeException {

	public TaskException() {

	}

	public TaskException(String exception) {
		System.out.println(exception);
	}
}

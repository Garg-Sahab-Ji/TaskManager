/*
* Class name: TaskException
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
* Description: Custom Exception used to throw exception
*/
package com.epam.taskmanager.exception;

public class TaskException extends RuntimeException {

	public TaskException() {

	}

	/**
	 * @param exception
	 */
	public TaskException(final String exception) {
		System.out.println(exception);
	}
}

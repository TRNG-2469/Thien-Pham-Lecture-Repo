package com.rev.jdbc.service;

/**
 * Indicates that an employee operation could not be completed by the database.
 */
public class EmployeeDataAccessException extends RuntimeException {

    public EmployeeDataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}

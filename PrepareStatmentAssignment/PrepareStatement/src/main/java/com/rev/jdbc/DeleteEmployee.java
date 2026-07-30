package com.rev.jdbc;

import com.rev.jdbc.dao.JdbcEmployeeDao;
import com.rev.jdbc.service.EmployeeDataAccessException;
import com.rev.jdbc.service.EmployeeService;

public class DeleteEmployee {

    public static void main(String[] args) {

        // ID of the employee that we want to delete.
        int employeeId = 4;

        EmployeeService employeeService =
                new EmployeeService(new JdbcEmployeeDao());

        try {
            if (employeeService.deleteEmployee(employeeId)) {
                System.out.println("Employee deleted successfully.");
            } else {
                System.out.println(
                        "No employee found with ID " + employeeId
                );
            }

        } catch (EmployeeDataAccessException exception) {
            System.err.println("Database error during deletion: "
                    + exception.getCause().getMessage());
        } catch (IllegalArgumentException | IllegalStateException exception) {
            System.err.println("Configuration or validation error: "
                    + exception.getMessage());
        }
    }
}

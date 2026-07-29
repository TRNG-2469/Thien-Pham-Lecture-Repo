package com.rev.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateEmployee {

    public static void main(String[] args) {

        // The employee whose salary we want to update.
        int employeeId = 4;

        // The new salary we want to assign.
        int newSalary = 3500;

        /*
         * First placeholder: the new salary.
         * Second placeholder: the employee ID used by WHERE.
         */
        String sql = """
                UPDATE employees
                SET salary = ?
                WHERE emp_id = ?
                """;

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement preparedStatement =
                        connection.prepareStatement(sql)
        ) {

            /*
             * Replace the first ? with the new salary.
             */
            preparedStatement.setInt(1, newSalary);

            /*
             * Replace the second ? with the employee ID.
             */
            preparedStatement.setInt(2, employeeId);

            /*
             * Run the UPDATE statement.
             *
             * The returned int tells us how many rows were updated.
             */
            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated == 1) {
                System.out.println("Employee updated successfully.");
            } else {
                System.out.println(
                        "No employee found with ID " + employeeId
                );
            }

        } catch (SQLException exception) {

            System.err.println(
                    "Database error during update: "
                            + exception.getMessage()
            );

        } catch (IllegalStateException exception) {

            System.err.println(
                    "Configuration error: " + exception.getMessage()
            );
        }
    }
}
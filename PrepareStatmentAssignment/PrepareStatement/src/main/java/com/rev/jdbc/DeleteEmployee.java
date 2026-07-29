package com.rev.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteEmployee {

    public static void main(String[] args) {

        // ID of the employee that we want to delete.
        int employeeId = 4;

        /*
         * The placeholder represents the employee ID.
         *
         * The WHERE clause is extremely important. Without WHERE,
         * every employee row would be deleted.
         */
        String sql = """
                DELETE FROM employees
                WHERE emp_id = ?
                """;

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement preparedStatement =
                        connection.prepareStatement(sql)
        ) {

            // Replace the first ? with employeeId.
            preparedStatement.setInt(1, employeeId);

            // Execute the DELETE and receive the number of deleted rows.
            int rowsDeleted = preparedStatement.executeUpdate();

            if (rowsDeleted == 1) {
                System.out.println("Employee deleted successfully.");
            } else {
                System.out.println(
                        "No employee found with ID " + employeeId
                );
            }

        } catch (SQLException exception) {

            System.err.println(
                    "Database error during deletion: "
                            + exception.getMessage()
            );

        } catch (IllegalStateException exception) {

            System.err.println(
                    "Configuration error: " + exception.getMessage()
            );
        }
    }
}
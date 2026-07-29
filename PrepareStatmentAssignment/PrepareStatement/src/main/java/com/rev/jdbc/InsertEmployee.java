package com.rev.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertEmployee {

    public static void main(String[] args) {

        // Values that we want to insert into the employees table.
        int employeeId = 4;
        String employeeName = "Diana";
        int employeeSalary = 2800;

        /*
         * The question marks are placeholders.
         *
         * We do not concatenate the Java values directly into the SQL.
         */
        String sql = """
                INSERT INTO employees (emp_id, name, salary)
                VALUES (?, ?, ?)
                """;

        /*
         * try-with-resources automatically closes both resources:
         *
         * 1. The database Connection
         * 2. The PreparedStatement
         *
         * They are closed even when an exception occurs.
         */
        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement preparedStatement =
                        connection.prepareStatement(sql)
        ) {

            /*
             * Replace the first question mark with employeeId.
             *
             * JDBC parameter positions start at 1, not 0.
             */
            preparedStatement.setInt(1, employeeId);

            // Replace the second question mark with employeeName.
            preparedStatement.setString(2, employeeName);

            // Replace the third question mark with employeeSalary.
            preparedStatement.setInt(3, employeeSalary);

            /*
             * executeUpdate() runs INSERT, UPDATE, or DELETE commands.
             *
             * It returns the number of database rows affected.
             */
            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted == 1) {
                System.out.println("Employee inserted successfully.");
            } else {
                System.out.println("Employee was not inserted.");
            }

        } catch (SQLException exception) {

            // Displays the database error message.
            System.err.println(
                    "Database error during insertion: "
                            + exception.getMessage()
            );

        } catch (IllegalStateException exception) {

            // Handles missing environment variables.
            System.err.println(
                    "Configuration error: " + exception.getMessage()
            );
        }
    }
}
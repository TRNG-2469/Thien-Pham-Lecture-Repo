package com.rev.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DatabaseConnection {

    /*
     * private constructor prevents someone from creating an unnecessary
     * DatabaseConnection object.
     *
     * We will call the static getConnection() method through the class.
     */
    private DatabaseConnection() {
    }

    /**
     * Creates and returns a connection to PostgreSQL.
     */
    public static Connection getConnection() throws SQLException {

        /*
         * System.getenv() reads a value from the operating system's
         * environment variables.
         */
        String url = System.getenv("DB_URL");
        String username = System.getenv("DB_USERNAME");
        String password = System.getenv("DB_PASSWORD");

        /*
         * Validate the variables before attempting the connection.
         *
         * If one is missing, the error message will be clearer than
         * a confusing database connection error.
         */
        if (url == null || url.isBlank()) {
            throw new IllegalStateException(
                    "Missing DB_URL environment variable."
            );
        }

        if (username == null || username.isBlank()) {
            throw new IllegalStateException(
                    "Missing DB_USERNAME environment variable."
            );
        }

        if (password == null || password.isBlank()) {
            throw new IllegalStateException(
                    "Missing DB_PASSWORD environment variable."
            );
        }

        /*
         * DriverManager uses the PostgreSQL JDBC driver to connect
         * to the database.
         *
         * This returns a Connection object representing the active
         * connection between Java and PostgreSQL.
         */
        return DriverManager.getConnection(url, username, password);
    }
}
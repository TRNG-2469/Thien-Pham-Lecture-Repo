package com.rev.web.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DatabaseConnection {
    private static final String DEFAULT_URL = "jdbc:postgresql://localhost:5433/todo_db";
    private static final String DEFAULT_USER = "postgres";
    private static final String DEFAULT_PASSWORD = "postgres";

    private DatabaseConnection() {
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                getSetting("DB_URL", DEFAULT_URL),
                getSetting("DB_USER", DEFAULT_USER),
                getSetting("DB_PASSWORD", DEFAULT_PASSWORD)
        );
    }

    private static String getSetting(String name, String defaultValue) {
        String value = System.getenv(name);
        return value == null || value.isBlank() ? defaultValue : value;
    }
}

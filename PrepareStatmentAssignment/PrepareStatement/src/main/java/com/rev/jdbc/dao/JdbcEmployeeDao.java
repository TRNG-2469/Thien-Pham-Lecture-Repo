package com.rev.jdbc.dao;

import com.rev.jdbc.DatabaseConnection;
import com.rev.jdbc.model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * PostgreSQL implementation of {@link EmployeeDao}.
 */
public class JdbcEmployeeDao implements EmployeeDao {

    private static final String INSERT_SQL = """
            INSERT INTO employees (emp_id, name, salary)
            VALUES (?, ?, ?)
            """;

    private static final String FIND_BY_ID_SQL = """
            SELECT emp_id, name, salary
            FROM employees
            WHERE emp_id = ?
            """;

    private static final String FIND_ALL_SQL = """
            SELECT emp_id, name, salary
            FROM employees
            ORDER BY emp_id
            """;

    private static final String UPDATE_SQL = """
            UPDATE employees
            SET name = ?, salary = ?
            WHERE emp_id = ?
            """;

    private static final String DELETE_SQL = """
            DELETE FROM employees
            WHERE emp_id = ?
            """;

    @Override
    public boolean create(Employee employee) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_SQL)) {

            statement.setInt(1, employee.id());
            statement.setString(2, employee.name());
            statement.setInt(3, employee.salary());
            return statement.executeUpdate() == 1;
        }
    }

    @Override
    public Optional<Employee> findById(int employeeId) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID_SQL)) {

            statement.setInt(1, employeeId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(toEmployee(resultSet));
                }
                return Optional.empty();
            }
        }
    }

    @Override
    public List<Employee> findAll() throws SQLException {
        List<Employee> employees = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_SQL);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                employees.add(toEmployee(resultSet));
            }
        }

        return employees;
    }

    @Override
    public boolean update(Employee employee) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_SQL)) {

            statement.setString(1, employee.name());
            statement.setInt(2, employee.salary());
            statement.setInt(3, employee.id());
            return statement.executeUpdate() == 1;
        }
    }

    @Override
    public boolean deleteById(int employeeId) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_SQL)) {

            statement.setInt(1, employeeId);
            return statement.executeUpdate() == 1;
        }
    }

    private Employee toEmployee(ResultSet resultSet) throws SQLException {
        return new Employee(
                resultSet.getInt("emp_id"),
                resultSet.getString("name"),
                resultSet.getInt("salary")
        );
    }
}

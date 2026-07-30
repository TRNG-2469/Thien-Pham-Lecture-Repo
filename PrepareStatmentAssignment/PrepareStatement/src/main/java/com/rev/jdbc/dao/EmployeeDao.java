package com.rev.jdbc.dao;

import com.rev.jdbc.model.Employee;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Defines the database operations available for employees.
 */
public interface EmployeeDao {

    boolean create(Employee employee) throws SQLException;

    Optional<Employee> findById(int employeeId) throws SQLException;

    List<Employee> findAll() throws SQLException;

    boolean update(Employee employee) throws SQLException;

    boolean deleteById(int employeeId) throws SQLException;
}

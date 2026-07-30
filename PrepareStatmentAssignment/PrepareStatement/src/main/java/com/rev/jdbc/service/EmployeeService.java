package com.rev.jdbc.service;

import com.rev.jdbc.dao.EmployeeDao;
import com.rev.jdbc.model.Employee;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Applies employee business rules before delegating persistence work to a DAO.
 */
public class EmployeeService {

    private final EmployeeDao employeeDao;

    public EmployeeService(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public boolean createEmployee(Employee employee) {
        validateEmployee(employee);
        return runDatabaseOperation(() -> employeeDao.create(employee));
    }

    public Optional<Employee> getEmployeeById(int employeeId) {
        validateEmployeeId(employeeId);
        return runDatabaseOperation(() -> employeeDao.findById(employeeId));
    }

    public List<Employee> getAllEmployees() {
        return runDatabaseOperation(employeeDao::findAll);
    }

    public boolean updateEmployee(Employee employee) {
        validateEmployee(employee);
        return runDatabaseOperation(() -> employeeDao.update(employee));
    }

    public boolean deleteEmployee(int employeeId) {
        validateEmployeeId(employeeId);
        return runDatabaseOperation(() -> employeeDao.deleteById(employeeId));
    }

    private void validateEmployee(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("Employee is required.");
        }

        validateEmployeeId(employee.id());

        if (employee.name() == null || employee.name().isBlank()) {
            throw new IllegalArgumentException("Employee name is required.");
        }

        if (employee.salary() < 0) {
            throw new IllegalArgumentException("Employee salary cannot be negative.");
        }
    }

    private void validateEmployeeId(int employeeId) {
        if (employeeId <= 0) {
            throw new IllegalArgumentException("Employee ID must be positive.");
        }
    }

    private <T> T runDatabaseOperation(DatabaseOperation<T> operation) {
        try {
            return operation.execute();
        } catch (SQLException exception) {
            throw new EmployeeDataAccessException(
                    "Unable to complete the employee database operation.",
                    exception
            );
        }
    }

    @FunctionalInterface
    private interface DatabaseOperation<T> {
        T execute() throws SQLException;
    }
}

package com.rev.web.dao;

import com.rev.web.config.DatabaseConnection;
import com.rev.web.model.Todo;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TodoDao {
    public List<Todo> findAll() {
        String sql = "SELECT id, title, completed FROM todos ORDER BY id";
        List<Todo> todos = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                todos.add(mapTodo(resultSet));
            }

            return todos;
        } catch (SQLException exception) {
            throw new IllegalStateException("Unable to retrieve todos", exception);
        }
    }

    public Todo findById(int id) {
        String sql = "SELECT id, title, completed FROM todos WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next() ? mapTodo(resultSet) : null;
            }
        } catch (SQLException exception) {
            throw new IllegalStateException("Unable to retrieve todo", exception);
        }
    }

    public Todo deleteById(int id) {
        String sql = "DELETE FROM todos WHERE id = ? RETURNING id, title, completed";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next() ? mapTodo(resultSet) : null;
            }
        } catch (SQLException exception) {
            throw new IllegalStateException("Unable to delete todo", exception);
        }
    }

    public Todo save(Todo todo) {
        String sql = "INSERT INTO todos (title, completed) VALUES (?, ?) RETURNING id, title, completed";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, todo.getTitle());
            statement.setBoolean(2, todo.isCompleted());
            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                return mapTodo(resultSet);
            }
        } catch (SQLException exception) {
            throw new IllegalStateException("Unable to create todo", exception);
        }
    }

    public Todo update(Todo todo) {
        String sql = "UPDATE todos SET title = ?, completed = ? WHERE id = ? "
                + "RETURNING id, title, completed";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, todo.getTitle());
            statement.setBoolean(2, todo.isCompleted());
            statement.setInt(3, todo.getId());
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next() ? mapTodo(resultSet) : null;
            }
        } catch (SQLException exception) {
            throw new IllegalStateException("Unable to update todo", exception);
        }
    }

    private Todo mapTodo(ResultSet resultSet) throws SQLException {
        return new Todo(
                resultSet.getInt("id"),
                resultSet.getBoolean("completed"),
                resultSet.getString("title")
        );
    }
}
